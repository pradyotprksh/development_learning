import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class FirebaseFirestoreService {
  final firestore = FirebaseFirestore.instance;

  Stream<List<MessageUserSavedDetails?>> getSavedMessages(String userId);

  Stream<DirectMessageDetails?> getMessageDetails(
    String currentUserId,
    String selectedUserId,
  );

  Stream<UsersGroupMessageDetails?> getGroupMessageWithUsersDetails(
    String selectedGroupId,
  );

  Stream<UserDetails?> getUserDetails(String userId);

  Stream<List<UserWithSingleStatusDetails>?> getStatus();

  Stream<List<UserDetails>> getUsersDetails(List<String> userIds);

  Future<void> createScreenshot(ScreenshotDetails screenshotDetails);

  Future<void> createMessageCopyForwardedSaved(
    MessageCopyForwardSavedDetails messageCopyForwardDetails,
  );

  Stream<List<DirectMessagesListUserDetails>?> getDirectMessagesFor(
    String currentUserId,
  );

  Stream<List<GroupMessageDetails>?> getGroupMessagesFor(
    String currentUserId,
  );

  Stream<SingleMessageDetails?>? getSingleMessageDetailsForDirectMessage(
    String messageId,
    String directMessageId, [
    bool isForSavedMessage = false,
  ]);

  Stream<SingleMessageDetails?>? getSingleMessageDetailsForGroupMessage(
    String messageId,
    String groupId, [
    bool isForSavedMessage = false,
  ]);

  Future<void> deleteAllSavedMessages(String userId);

  Stream<List<SingleMessageDetails>> getDirectMessages(String directMessageId);

  Stream<List<SingleMessageDetails>> getGroupMessages(String groupMessageId);

  DocumentReference<UserDetails> getUserDocumentReference(String userId);

  Future<void> setUserLogInHistory(LoginHistoryDetails loginHistoryDetails);

  Future<void> setContactAvailableDetails(
    String userId,
    ContactsAvailableDetails contactsAvailableDetails,
  );

  Stream<List<UserGroupCallDetails>> getCurrentUserCalls(String userId);

  Future<void> createAnIssue(ContactUsDetails contactUsDetails);

  Future<void> createCall(CallDetails callDetails);

  Future<bool> isContactsAvailableListPresent(String userId);

  Future<bool> isContactsNotAvailableListPresent(String userId);

  Future<void> setContactNotAvailableDetails(
    String userId,
    ContactsNotAvailableDetails contactsNotAvailableDetails,
  );

  Future<List<String>> deleteStatusOnTimeCompletion(String userId);

  StreamController<List<UserContactsAvailableDetails>?>
      getUserContactsAvailable(
    String userId,
  );

  Stream<List<ContactsNotAvailableDetails>?> getUserContactsNotAvailable(
    String userId,
  );

  Future<void> updateUserDetails(String userId, Map<String, Object> values);

  Future<void> setUserDetails(String userId, UserDetails userDetails);

  Future<void> setStatus(StatusDetails statusDetails);

  Future<String> createDirectMessage(DirectMessageDetails directMessageDetails);

  Future<void> updateDirectMessage(
    String messageId,
    Map<String, Object> values,
  );

  Future<void> updateGroupMessage(
    String messageId,
    Map<String, Object> values,
  );

  Future<String> createGroupMessage(GroupMessageDetails groupMessageDetails);

  Future<void> sendDirectMessage(
    SingleMessageDetails singleMessageDetails,
    String directMessageId,
  );

  Future<void> sendGroupMessage(
    SingleMessageDetails singleMessageDetails,
    String groupMessageId,
  );

  Future<void> setStatusSeen(
    String statusId,
    StatusSeenDetails statusSeenDetails,
  );

  Future<UserDetails?> getUserAccountByPhoneNumber(String phoneNumber);

  Future<UserDetails?> getUserAccountByEmailAddress(String emailAddress);

  Future<void> clearCallLogs(String userId);

  Future<void> saveMessage(
    String userId,
    String messageId,
    SavedMessageDetails savedMessageDetails,
  );

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

  CollectionReference<ContactUsDetails>
      getContactUsDetailsCollectionReference() => _getCollectionReference(
            CoreConstants.contactUsCollection,
            ContactUsDetails.fromFirestore,
            (ContactUsDetails loginHistoryDetails, _) =>
                loginHistoryDetails.toFirestore(),
          );

  CollectionReference<SavedMessageDetails> getSavedMessagesCollectionReference(
          String userId) =>
      _getCollectionReference(
        CoreConstants.savedMessagesCollection
            .replaceAll(CoreConstants.userIdPlaceholder, userId),
        SavedMessageDetails.fromFirestore,
        (SavedMessageDetails loginHistoryDetails, _) =>
            loginHistoryDetails.toFirestore(),
      );

  CollectionReference<CallDetails> getCallDetailsCollectionReference(
          String userId) =>
      _getCollectionReference(
        CoreConstants.callsCollection
            .replaceAll(CoreConstants.userIdPlaceholder, userId),
        CallDetails.fromFirestore,
        (CallDetails loginHistoryDetails, _) =>
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

  CollectionReference<GroupMessageDetails>
      getGroupMessageCollectionReference() => _getCollectionReference(
            CoreConstants.groupMessageCollection,
            GroupMessageDetails.fromFirestore,
            (GroupMessageDetails groupMessageDetails, _) =>
                groupMessageDetails.toFirestore(),
          );

  CollectionReference<ScreenshotDetails> getScreenshotCollectionReference(
          String userId) =>
      _getCollectionReference(
        CoreConstants.screenshotCollection.replaceAll(
          CoreConstants.userIdPlaceholder,
          userId,
        ),
        ScreenshotDetails.fromFirestore,
        (ScreenshotDetails groupMessageDetails, _) =>
            groupMessageDetails.toFirestore(),
      );

  CollectionReference<MessageCopyForwardSavedDetails>
      getMessageCopyForwardSavedCollectionReference(
    MessageCopyForwardSavedDetails details,
  ) {
    final collectionName =
        CoreConstants.messageCopyForwardedSavedCollection.replaceAll(
      CoreConstants.messageCopyForwardedSavedEndpointPlaceholder,
      details.isSaved
          ? CoreConstants.messageSaved
          : details.isCopied
              ? CoreConstants.messageCopied
              : CoreConstants.messageForwarded,
    );

    return _getCollectionReference(
      collectionName.replaceAll(
        CoreConstants.userIdPlaceholder,
        details.userId,
      ),
      MessageCopyForwardSavedDetails.fromFirestore,
      (MessageCopyForwardSavedDetails groupMessageDetails, _) =>
          groupMessageDetails.toFirestore(),
    );
  }

  CollectionReference<SingleMessageDetails>
      getDirectMessagesCollectionReference(
    String messageId,
  ) =>
          _getCollectionReference(
            CoreConstants.directMessagesCollection
                .replaceAll(CoreConstants.messageIdPlaceholder, messageId),
            SingleMessageDetails.fromFirestore,
            (SingleMessageDetails singleMessageDetails, _) =>
                singleMessageDetails.toFirestore(),
          );

  CollectionReference<SingleMessageDetails> getGroupMessagesCollectionReference(
    String messageId,
  ) =>
      _getCollectionReference(
        CoreConstants.groupMessagesCollection
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
