import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class FirebaseFirestoreService {
  final firestore = FirebaseFirestore.instance;

  StreamController<DirectMessageDetails?> getMessageDetails(
    String currentUserId,
    String selectedUserId,
  );

  StreamController<UserDetails?> getUserDetails(String userId);

  StreamController<List<UserWithSingleStatusDetails>?> getStatus();

  StreamController<List<DirectMessagesListUserDetails>?>
      getDirectMessagesForCurrentUser(
    String currentUserId,
  );

  DocumentReference<UserDetails> getUserDocumentReference(String userId);

  Future<void> setUserLogInHistory(LoginHistoryDetails loginHistoryDetails);

  Future<void> setContactAvailableDetails(
    String userId,
    ContactsAvailableDetails contactsAvailableDetails,
  );

  Future<bool> isContactsAvailableListPresent(String userId);

  Future<bool> isContactsNotAvailableListPresent(String userId);

  Future<void> setContactNotAvailableDetails(
    String userId,
    ContactsNotAvailableDetails contactsNotAvailableDetails,
  );

  StreamController<List<UserContactsAvailableDetails>?>
      getUserContactsAvailable(
    String userId,
  );

  StreamController<List<ContactsNotAvailableDetails>?>
      getUserContactsNotAvailable(
    String userId,
  );

  Future<void> updateUserDetails(String userId, Map<String, Object> values);

  Future<void> setUserDetails(String userId, UserDetails userDetails);

  Future<void> setStatus(StatusDetails statusDetails);

  Future<void> createDirectMessage(DirectMessageDetails directMessageDetails);

  Future<void> sendMessage(
    SingleMessageDetails singleMessageDetails,
    String directMessageId,
  );

  Future<void> setStatusSeen(
    String statusId,
    StatusSeenDetails statusSeenDetails,
  );

  Future<UserDetails?> getUserAccountByPhoneNumber(String phoneNumber);

  Future<UserDetails?> getUserAccountByEmailAddress(String emailAddress);

  CollectionReference<R> _getCollectionReference<R>(
    String collectionPath,
    FromFirestore<R> fromFirestore,
    ToFirestore<R> toFirestore,
  ) =>
      firestore.collection(collectionPath).withConverter(
            fromFirestore: fromFirestore,
            toFirestore: toFirestore,
          );

  CollectionReference<ContactsAvailableDetails>
      getContactAvailableDetailsCollectionReference(String userId) =>
          _getCollectionReference(
            CoreConstants.contactsAvailableCollection
                .replaceAll(CoreConstants.userIdPlaceholder, userId),
            ContactsAvailableDetails.fromFirestore,
            (ContactsAvailableDetails loginHistoryDetails, _) =>
                loginHistoryDetails.toFirestore(),
          );

  CollectionReference<ContactsNotAvailableDetails>
      getContactNotAvailableDetailsCollectionReference(String userId) =>
          _getCollectionReference(
            CoreConstants.contactsNotAvailableCollection
                .replaceAll(CoreConstants.userIdPlaceholder, userId),
            ContactsNotAvailableDetails.fromFirestore,
            (ContactsNotAvailableDetails loginHistoryDetails, _) =>
                loginHistoryDetails.toFirestore(),
          );

  CollectionReference<LoginHistoryDetails> getLoginHistoryCollectionReference(
          String userId) =>
      _getCollectionReference(
        CoreConstants.loginHistoryCollection
            .replaceAll(CoreConstants.userIdPlaceholder, userId),
        LoginHistoryDetails.fromFirestore,
        (LoginHistoryDetails loginHistoryDetails, _) =>
            loginHistoryDetails.toFirestore(),
      );

  CollectionReference<DirectMessageDetails>
      getDirectMessageCollectionReference() => _getCollectionReference(
            CoreConstants.directMessageCollection,
            DirectMessageDetails.fromFirestore,
            (DirectMessageDetails directMessageDetails, _) =>
                directMessageDetails.toFirestore(),
          );

  CollectionReference<SingleMessageDetails> getMessageCollectionReference(
    String messageId,
  ) =>
      _getCollectionReference(
        CoreConstants.messagesCollection
            .replaceAll(CoreConstants.messageIdPlaceholder, messageId),
        SingleMessageDetails.fromFirestore,
        (SingleMessageDetails singleMessageDetails, _) =>
            singleMessageDetails.toFirestore(),
      );

  CollectionReference<UserDetails> getUserCollectionReference() =>
      _getCollectionReference<UserDetails>(
        CoreConstants.userCollection,
        UserDetails.fromFirestore,
        (UserDetails userDetails, _) => userDetails.toFirestore(),
      );

  CollectionReference<StatusDetails> getStatusCollectionReference() =>
      _getCollectionReference<StatusDetails>(
        CoreConstants.statusCollection,
        StatusDetails.fromFirestore,
        (StatusDetails statusDetails, _) => statusDetails.toFirestore(),
      );

  CollectionReference<StatusSeenDetails> getStatusSeenCollectionReference(
    String statusId,
  ) =>
      _getCollectionReference<StatusSeenDetails>(
        CoreConstants.statusSeenCollection.replaceAll(
          CoreConstants.statusIdPlaceholder,
          statusId,
        ),
        StatusSeenDetails.fromFirestore,
        (StatusSeenDetails statusSeenDetails, _) =>
            statusSeenDetails.toFirestore(),
      );
}
