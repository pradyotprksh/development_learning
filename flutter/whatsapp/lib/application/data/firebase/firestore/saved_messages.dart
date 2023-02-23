import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

mixin FirestoreSavedMessagesService implements FirebaseFirestoreService {
  @override
  Future<void> saveMessage(
    String userId,
    String messageId,
    SavedMessageDetails savedMessageDetails,
  ) async {
    await getSavedMessagesCollectionReference(userId).doc(messageId).set(
          savedMessageDetails,
        );
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.savedMessage,
        ListenersType.write,
        savedMessageDetails.calculateSize,
      ),
    );
  }

  @override
  Stream<List<MessageUserSavedDetails?>> getSavedMessages(String userId) =>
      getSavedMessagesCollectionReference(userId).snapshots().map(
            (event) => event.docs.map(
              (e) {
                final savedMessageDetails = e.data();
                final messagePath = savedMessageDetails.messageId;
                final messagePathSplit = messagePath.split('/');
                Stream<SingleMessageDetails?>? singleMessageDetails;
                if (messagePathSplit.length == 3) {
                  try {
                    if (messagePathSplit.first ==
                        CoreConstants.directMessageCollection) {
                      singleMessageDetails =
                          getSingleMessageDetailsForDirectMessage(
                        messagePathSplit[2],
                        messagePathSplit[1],
                        true,
                      );
                    } else if (messagePathSplit.first ==
                        CoreConstants.groupMessageCollection) {
                      singleMessageDetails =
                          getSingleMessageDetailsForGroupMessage(
                        messagePathSplit[2],
                        messagePathSplit[1],
                        true,
                      );
                    }
                  } catch (e) {
                    FirebaseUtils.recordFlutterError(e);
                  }
                }
                if (singleMessageDetails == null) {
                  return null;
                }
                return MessageUserSavedDetails(
                  savedMessageDetails,
                  getUserDetails(
                    savedMessageDetails.messageSentByUserId,
                  ),
                  singleMessageDetails,
                );
              },
            ).toList(),
          );

  @override
  Future<void> deleteAllSavedMessages(String userId) async {
    final batch = firestore.batch();
    final callLogs = await getSavedMessagesCollectionReference(userId).get();
    for (var call in callLogs.docs) {
      batch.delete(call.reference);
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.savedMessage,
          ListenersType.write,
          call.data().size,
        ),
      );
    }
    await batch.commit();
  }
}
