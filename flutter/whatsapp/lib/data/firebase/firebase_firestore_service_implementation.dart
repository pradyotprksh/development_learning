import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:collection/collection.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService {
  factory FirebaseFirestoreServiceImplementation() => _instance;

  FirebaseFirestoreServiceImplementation._privateConstructor();

  static final FirebaseFirestoreServiceImplementation _instance =
      FirebaseFirestoreServiceImplementation._privateConstructor();

  @override
  StreamController<UserDetails?> getUserDetails(String userId) {
    var userDetails = StreamController<UserDetails?>();
    final userRef = getUserCollectionReference().doc(userId);
    userRef.snapshots().listen(
      (event) {
        userDetails.add(event.data());
      },
    );
    return userDetails;
  }

  @override
  Future<void> setUserDetails(String userId, UserDetails userDetails) async {
    final userRef = getUserCollectionReference().doc(userId);
    await userRef.set(userDetails);
  }

  @override
  Future<UserDetails?> getUserAccountByEmailAddress(String emailAddress) async {
    try {
      final usersCollection = await getUserCollectionReference()
          .where(
            FirestoreItemKey.emailId,
            isEqualTo: emailAddress.replaceAll(' ', ''),
          )
          .limit(1)
          .get();
      return usersCollection.docs.first.data();
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return null;
    }
  }

  @override
  Future<UserDetails?> getUserAccountByPhoneNumber(String phoneNumber) async {
    try {
      final usersCollection = await getUserCollectionReference()
          .where(
            FirestoreItemKey.phoneNumber,
            isEqualTo: phoneNumber.replaceAll(' ', ''),
          )
          .limit(1)
          .get();
      return usersCollection.docs.first.data();
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return null;
    }
  }

  @override
  Future<void> setStatus(StatusDetails statusDetails) async {
    await getStatusCollectionReference().add(statusDetails);
  }

  @override
  StreamController<List<UserWithSingleStatusDetails>?> getStatus() {
    var status = StreamController<List<UserWithSingleStatusDetails>?>();
    getStatusCollectionReference()
        .orderBy(
          FirestoreItemKey.createdOnTimeStamp,
          descending: true,
        )
        .snapshots()
        .listen(
      (event) {
        var statusWithUserDetails = <UserWithSingleStatusDetails>[];
        for (var element in event.docs) {
          final statusDetails = element.data();
          final userId = statusDetails.userId;

          final isUserPresent = statusWithUserDetails.where(
            (element) => element.userId == userId,
          );
          if (isUserPresent.isNotEmpty) {
            final isStatusPresent = isUserPresent.first.statusDetails.where(
              (element) => element.statusId == statusDetails.statusId,
            );
            if (isStatusPresent.isNotEmpty) {
              continue;
            }
          }

          final allStatus = event.docs
              .where(
                (element) => element.data().userId == userId,
              )
              .map((e) => e.data())
              .toList();

          statusWithUserDetails.add(
            UserWithSingleStatusDetails(
              userId,
              allStatus,
              getUserDetails(userId),
            ),
          );
        }
        status.add(statusWithUserDetails);
      },
    );
    return status;
  }

  @override
  Future<void> setStatusSeen(
    String statusId,
    StatusSeenDetails statusSeenDetails,
  ) async {
    final isAlreadyAvailable = await getStatusSeenCollectionReference(statusId)
        .doc(statusSeenDetails.userId)
        .get();
    if (!isAlreadyAvailable.exists) {
      await getStatusSeenCollectionReference(statusId)
          .doc(statusSeenDetails.userId)
          .set(statusSeenDetails);
    }
  }

  @override
  DocumentReference<UserDetails> getUserDocumentReference(String userId) =>
      getUserCollectionReference().doc(userId);

  @override
  StreamController<DirectMessageDetails?> getMessageDetails(
    String currentUserId,
    String selectedUserId,
  ) {
    final directMessageDetails = StreamController<DirectMessageDetails?>();
    getDirectMessageCollectionReference()
        .where(
          FirestoreItemKey.users,
          arrayContains: currentUserId,
        )
        .snapshots()
        .listen(
      (event) {
        var found = false;
        for (var element in event.docs) {
          final users = element.data().users;
          if (users.contains(currentUserId) && users.contains(selectedUserId)) {
            directMessageDetails.add(event.docs.firstOrNull?.data());
            found = true;
            break;
          }
        }
        if (!found) {
          directMessageDetails.add(null);
        }
      },
    );
    return directMessageDetails;
  }

  @override
  Future<void> createDirectMessage(
    DirectMessageDetails directMessageDetails,
  ) async {
    await getDirectMessageCollectionReference().add(directMessageDetails);
  }

  @override
  Future<void> sendMessage(
    SingleMessageDetails singleMessageDetails,
    String directMessageId,
  ) async {
    await getMessageCollectionReference(directMessageId)
        .add(singleMessageDetails);
  }

  @override
  Future<void> setUserLogInHistory(
      LoginHistoryDetails loginHistoryDetails) async {
    final isAvailable =
        await getLoginHistoryCollectionReference(loginHistoryDetails.userId)
            .doc(DeviceUtilsMethods.getCurrentDateWithCurrentHour())
            .get();
    if (!isAvailable.exists) {
      await getLoginHistoryCollectionReference(loginHistoryDetails.userId)
          .doc(DeviceUtilsMethods.getCurrentDateWithCurrentHour())
          .set(loginHistoryDetails);
    }
  }

  @override
  Future<void> updateUserDetails(
      String userId, Map<String, Object> values) async {
    final userRef = getUserCollectionReference().doc(userId);
    await userRef.update(values);
  }

  @override
  StreamController<List<DirectMessagesListUserDetails>?>
      getDirectMessagesForCurrentUser(
    String currentUserId,
  ) {
    final messageListUserDetails =
        StreamController<List<DirectMessagesListUserDetails>?>();

    getDirectMessageCollectionReference()
        .where(
          FirestoreItemKey.users,
          arrayContains: currentUserId,
        )
        .snapshots()
        .listen(
      (event) {
        var messagesDetails = <DirectMessagesListUserDetails>[];
        for (var doc in event.docs) {
          final details = doc.data();
          final otherUserId = details.users.firstWhereOrNull(
            (element) => element != currentUserId,
          );
          if (otherUserId != null) {
            messagesDetails.add(
              DirectMessagesListUserDetails(
                doc.data(),
                getUserDetails(otherUserId),
              ),
            );
          }
        }
        messageListUserDetails.add(messagesDetails);
      },
    );
    return messageListUserDetails;
  }

  @override
  Future<void> setContactAvailableDetails(
    String userId,
    ContactsAvailableDetails contactsAvailableDetails,
  ) async {
    final updatedDetails = contactsAvailableDetails.updateDocumentReference(
      getUserCollectionReference().doc(contactsAvailableDetails.userId),
    );

    await getContactAvailableDetailsCollectionReference(userId)
        .doc(contactsAvailableDetails.userId)
        .set(
          updatedDetails,
        );
  }

  @override
  StreamController<List<UserContactsAvailableDetails>?>
      getUserContactsAvailable(
    String userId,
  ) {
    final userContactsAvailableDetails =
        StreamController<List<UserContactsAvailableDetails>?>();
    getContactAvailableDetailsCollectionReference(userId).snapshots().listen(
      (event) async {
        var contacts = <UserContactsAvailableDetails>[];
        for (var doc in event.docs) {
          final userDetails =
              await getUserCollectionReference().doc(doc.data().userId).get();
          contacts.add(
            UserContactsAvailableDetails(
              userDetails.data(),
              doc.data(),
              false,
            ),
          );
        }
        userContactsAvailableDetails.add(contacts);
      },
    );
    return userContactsAvailableDetails;
  }

  @override
  StreamController<List<ContactsNotAvailableDetails>?>
      getUserContactsNotAvailable(
    String userId,
  ) {
    final userContactsNotAvailableDetails =
        StreamController<List<ContactsNotAvailableDetails>?>();
    getContactNotAvailableDetailsCollectionReference(userId).snapshots().listen(
      (event) {
        final data = event.docs.map((e) => e.data()).toList();
        userContactsNotAvailableDetails.add(
          data,
        );
      },
    );
    return userContactsNotAvailableDetails;
  }

  @override
  Future<void> setContactNotAvailableDetails(
    String userId,
    ContactsNotAvailableDetails contactsNotAvailableDetails,
  ) async {
    if (contactsNotAvailableDetails.shouldAdd()) {
      await getContactNotAvailableDetailsCollectionReference(userId)
          .doc(
            contactsNotAvailableDetails.getDocId(),
          )
          .set(contactsNotAvailableDetails);
    }
  }

  @override
  Future<bool> isContactsAvailableListPresent(String userId) async {
    final details =
        await getContactAvailableDetailsCollectionReference(userId).get();
    return details.size > 0;
  }

  @override
  Future<bool> isContactsNotAvailableListPresent(String userId) async {
    final details =
        await getContactNotAvailableDetailsCollectionReference(userId).get();
    return details.size > 0;
  }

  @override
  Future<void> createGroupMessage(
      GroupMessageDetails groupMessageDetails) async {
    await getGroupMessageCollectionReference().add(groupMessageDetails);
  }
}
