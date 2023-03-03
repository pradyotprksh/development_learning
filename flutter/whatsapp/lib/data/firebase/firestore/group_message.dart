import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreGroupMessageService implements FirebaseFirestoreService {
  @override
  Future<String> createGroupMessage(
      GroupMessageDetails groupMessageDetails) async {
    final group =
        await getGroupMessageCollectionReference().add(groupMessageDetails);
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.groupMessages,
        ListenersType.write,
        groupMessageDetails.calculateSize,
      ),
    );
    return group.id;
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
            (event) => event.docs.map(
              (e) {
                final data = e.data();
                NetworkListeners.listener.add(
                  Listener(
                    ListenersFor.groupMessages,
                    ListenersType.read,
                    data.size,
                  ),
                );
                return data;
              },
            ).toList(),
          );

  @override
  Stream<UsersGroupMessageDetails?> getGroupMessageWithUsersDetails(
    String selectedGroupId,
  ) =>
      getGroupMessageCollectionReference().doc(selectedGroupId).snapshots().map(
        (event) {
          final groupDetails = event.data();
          final userIds = groupDetails?.users ?? [];

          NetworkListeners.listener.add(
            Listener(
              ListenersFor.groupMessages,
              ListenersType.read,
              groupDetails?.size ?? 0,
            ),
          );

          return UsersGroupMessageDetails(
            getUsersDetails(userIds),
            groupDetails,
          );
        },
      );

  @override
  Future<void> sendGroupMessage(
    SingleMessageDetails singleMessageDetails,
    String groupMessageId,
  ) async {
    await getGroupMessagesCollectionReference(groupMessageId)
        .add(singleMessageDetails);

    NetworkListeners.listener.add(
      Listener(
        ListenersFor.singleMessage,
        ListenersType.write,
        singleMessageDetails.calculateSize,
      ),
    );
  }

  @override
  Stream<List<SingleMessageDetails>> getGroupMessages(String groupMessageId) =>
      getGroupMessagesCollectionReference(groupMessageId)
          .orderBy(FirestoreItemKey.sentOnTimeStamp, descending: true)
          .snapshots()
          .map(
            (event) => event.docs.map(
              (e) {
                final data = e.data();
                NetworkListeners.listener.add(
                  Listener(
                    ListenersFor.singleMessage,
                    ListenersType.read,
                    data.size,
                  ),
                );
                return data;
              },
            ).toList(),
          );

  @override
  Future<void> updateGroupMessage(
    String messageId,
    Map<String, Object> values,
  ) async {
    await getGroupMessageCollectionReference().doc(messageId).update(values);
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.groupMessages,
        ListenersType.write,
        values.getDocumentSize().toDouble(),
      ),
    );
  }

  @override
  Stream<SingleMessageDetails?>? getSingleMessageDetailsForGroupMessage(
    String messageId,
    String groupId, [
    bool isForSavedMessage = false,
  ]) =>
      getGroupMessagesCollectionReference(groupId)
          .doc(messageId)
          .snapshots()
          .map(
        (event) {
          NetworkListeners.listener.add(
            Listener(
              isForSavedMessage
                  ? ListenersFor.savedMessage
                  : ListenersFor.directMessages,
              ListenersType.read,
              event.data()?.size ?? 0,
            ),
          );
          return event.data();
        },
      );

  @override
  Stream<List<FileInformationDetails>> getGroupMessagesAttachments(
    String groupMessageId,
  ) =>
      getGroupMessagesCollectionReference(groupMessageId)
          .orderBy(FirestoreItemKey.sentOnTimeStamp, descending: true)
          .snapshots()
          .map(
        (event) {
          final docs = event.docs;
          final data = docs.map(
            (e) {
              final details = e.data();
              NetworkListeners.listener.add(
                Listener(
                  ListenersFor.singleMessage,
                  ListenersType.read,
                  details.size,
                ),
              );
              return details;
            },
          ).toList();

          final attachments = data.map((e) => e.attachments).toList()
            ..removeWhere(
              (element) => element == null || element.isEmpty,
            );

          final allAttachments = attachments
              .expand<FileInformationDetails>(
                (element) => element as List<FileInformationDetails>,
              )
              .toList();

          return allAttachments;
        },
      );
}
