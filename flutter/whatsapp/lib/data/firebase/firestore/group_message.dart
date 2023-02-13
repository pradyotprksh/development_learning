import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirebaseGroupMessageService implements FirebaseFirestoreService {
  @override
  Future<String> createGroupMessage(
      GroupMessageDetails groupMessageDetails) async {
    final group =
        await getGroupMessageCollectionReference().add(groupMessageDetails);
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
            (event) => event.docs.map((e) => e.data()).toList(),
          );

  @override
  Stream<UsersGroupMessageDetails?> getGroupMessageWithUsersDetails(
    String selectedGroupId,
  ) =>
      getGroupMessageCollectionReference().doc(selectedGroupId).snapshots().map(
        (event) {
          final groupDetails = event.data();
          final userIds = groupDetails?.users ?? [];

          return UsersGroupMessageDetails(
            getUsersDetails(userIds),
            event.data(),
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
  }

  @override
  Stream<List<SingleMessageDetails>> getGroupMessages(String groupMessageId) =>
      getGroupMessagesCollectionReference(groupMessageId)
          .orderBy(FirestoreItemKey.sentOnTimeStamp, descending: true)
          .snapshots()
          .map(
            (event) => event.docs.map((e) => e.data()).toList(),
          );

  @override
  Future<void> updateGroupMessage(
    String messageId,
    Map<String, Object> values,
  ) async {
    await getGroupMessageCollectionReference().doc(messageId).update(values);
  }
}
