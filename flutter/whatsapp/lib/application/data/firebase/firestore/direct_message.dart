import 'package:collection/collection.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

mixin FirestoreDirectMessageService implements FirebaseFirestoreService {
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
              directMessageDetails = event.docs
                  .firstWhereOrNull((element) =>
                      element.data().users.contains(currentUserId) &&
                      element.data().users.contains(selectedUserId))
                  ?.data();
              found = true;
              break;
            }
          }
          if (!found) {
            directMessageDetails = null;
          }
          NetworkListeners.listener.add(
            Listener(
              ListenersFor.directMessages,
              ListenersType.read,
              directMessageDetails?.size ?? 0,
            ),
          );
          return directMessageDetails;
        },
      );

  @override
  Future<void> updateDirectMessage(
    String messageId,
    Map<String, Object> values,
  ) async {
    await getDirectMessageCollectionReference().doc(messageId).update(values);
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.directMessages,
        ListenersType.write,
        values.getDocumentSize().toDouble(),
      ),
    );
  }

  @override
  Future<String> createDirectMessage(
    DirectMessageDetails directMessageDetails,
  ) async {
    final directMessage =
        await getDirectMessageCollectionReference().add(directMessageDetails);
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.directMessages,
        ListenersType.write,
        directMessageDetails.calculateSize,
      ),
    );
    return directMessage.id;
  }

  @override
  Future<void> sendDirectMessage(
    SingleMessageDetails singleMessageDetails,
    String directMessageId,
  ) async {
    await getDirectMessagesCollectionReference(directMessageId)
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
  Stream<List<SingleMessageDetails>> getDirectMessages(
          String directMessageId) =>
      getDirectMessagesCollectionReference(directMessageId)
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
                  getUserDetails(otherUserId),
                ),
              );
              NetworkListeners.listener.add(
                Listener(
                  ListenersFor.directMessages,
                  ListenersType.read,
                  doc.data().size,
                ),
              );
            }
          }
          return messagesDetails;
        },
      );

  @override
  Stream<SingleMessageDetails?>? getSingleMessageDetailsForDirectMessage(
    String messageId,
    String directMessageId, [
    bool isForSavedMessage = false,
  ]) =>
      getDirectMessagesCollectionReference(directMessageId)
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
}
