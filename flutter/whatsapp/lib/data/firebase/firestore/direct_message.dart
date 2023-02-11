import 'package:collection/collection.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirebaseDirectMessageService implements FirebaseFirestoreService {
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
}
