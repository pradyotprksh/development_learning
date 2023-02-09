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
  Stream<UserDetails?> getUserDetails(String userId) =>
      getUserCollectionReference().doc(userId).snapshots().map(
            (event) => event.data(),
          );

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
  Stream<List<UserWithSingleStatusDetails>?> getStatus() =>
      getStatusCollectionReference()
          .orderBy(
            FirestoreItemKey.createdOnTimeStamp,
            descending: true,
          )
          .snapshots()
          .map(
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
                getUserCollectionReference()
                    .doc(userId)
                    .snapshots()
                    .map((event) => event.data()),
              ),
            );
          }
          return statusWithUserDetails;
        },
      );

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
  Stream<DirectMessageDetails?> getMessageDetails(
    String currentUserId,
    String selectedUserId,
  ) =>
      getDirectMessageCollectionReference()
          .where(
            FirestoreItemKey.users,
            arrayContains: currentUserId,
          )
          .snapshots()
          .map(
        (event) {
          DirectMessageDetails? directMessageDetails;
          var found = false;
          for (var element in event.docs) {
            final users = element.data().users;
            if (users.contains(currentUserId) &&
                users.contains(selectedUserId)) {
              directMessageDetails = event.docs.firstOrNull?.data();
              found = true;
              break;
            }
          }
          if (!found) {
            directMessageDetails = null;
          }
          return directMessageDetails;
        },
      );

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
    LoginHistoryDetails loginHistoryDetails,
  ) async {
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
  Stream<List<DirectMessagesListUserDetails>?> getDirectMessagesFor(
    String currentUserId,
  ) =>
      getDirectMessageCollectionReference()
          .where(
            FirestoreItemKey.users,
            arrayContains: currentUserId,
          )
          .snapshots()
          .map(
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
                  getUserCollectionReference().doc(otherUserId).snapshots().map(
                        (event) => event.data(),
                      ),
                ),
              );
            }
          }
          return messagesDetails;
        },
      );

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
  Stream<List<ContactsNotAvailableDetails>?> getUserContactsNotAvailable(
    String userId,
  ) =>
      getContactNotAvailableDetailsCollectionReference(userId).snapshots().map(
            (event) => event.docs.map((e) => e.data()).toList(),
          );

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

  @override
  Future<void> deleteStatusOnTimeCompletion(String userId) async {
    final allCurrentUserStatus = await getStatusCollectionReference()
        .where(
          FirestoreItemKey.userId,
          isEqualTo: userId,
        )
        .get();
    for (var status in allCurrentUserStatus.docs) {
      final statusTime = status.data().createdOnTimeStamp;
      if (DeviceUtilsMethods.getTimeDifference(statusTime) >=
          FirebaseRemoteConfigService.statusDeleteTimeValue()) {
        await getStatusCollectionReference().doc(status.id).delete();
      }
    }
  }

  @override
  Stream<List<GroupMessageDetails>?> getGroupMessagesFor(
    String currentUserId,
  ) =>
      getGroupMessageCollectionReference()
          .where(
            FirestoreItemKey.users,
            arrayContains: currentUserId,
          )
          .snapshots()
          .map(
            (event) => event.docs.map((e) => e.data()).toList(),
          );

  @override
  Stream<UsersGroupMessageDetails?> getGroupMessageWithUsersDetails(
    String selectedGroupId,
  ) =>
      getGroupMessageCollectionReference()
          .doc(selectedGroupId)
          .snapshots()
          .map((event) {
        final groupDetails = event.data();
        final userIds = groupDetails?.users ?? [];

        return UsersGroupMessageDetails(
          getUsersDetails(userIds),
          event.data(),
        );
      });

  @override
  Stream<List<UserDetails>> getUsersDetails(List<String> userIds) =>
      getUserCollectionReference()
          .where(FirestoreItemKey.userId, whereIn: userIds)
          .snapshots()
          .map(
            (event) => event.docs.map((e) => e.data()).toList(),
          );

  @override
  Future<void> createCall(CallDetails callDetails) async {
    for (var userId in callDetails.usersId) {
      await getCallDetailsCollectionReference(userId).add(callDetails);
    }
  }

  @override
  Stream<List<UserGroupCallDetails>> getCurrentUserCalls(String userId) =>
      getCallDetailsCollectionReference(userId)
          .orderBy(FirestoreItemKey.createdOnTimeStamp, descending: true)
          .snapshots()
          .map(
            (event) => event.docs.map(
              (e) {
                final callDetails = e.data();
                Stream<UsersGroupMessageDetails?>? groupMessageDetails;
                Stream<List<UserDetails>?>? userDetails;

                if (callDetails.isGroupCall) {
                  final groupId = callDetails.groupId;
                  if (groupId != null) {
                    groupMessageDetails =
                        getGroupMessageWithUsersDetails(groupId);
                  }
                } else {
                  userDetails = getUsersDetails(
                    callDetails.usersId,
                  );
                }

                return UserGroupCallDetails(
                  callDetails,
                  groupMessageDetails,
                  userDetails,
                );
              },
            ).toList(),
          );

  @override
  Future<void> clearCallLogs(String userId) async {
    final callLogs = await getCallDetailsCollectionReference(userId).get();
    for (var call in callLogs.docs) {
      await call.reference.delete();
    }
  }
}
