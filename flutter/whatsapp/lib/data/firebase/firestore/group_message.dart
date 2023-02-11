import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirebaseGroupMessageService implements FirebaseFirestoreService {
  @override
  Future<void> createGroupMessage(
      GroupMessageDetails groupMessageDetails) async {
    await getGroupMessageCollectionReference().add(groupMessageDetails);
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
}
