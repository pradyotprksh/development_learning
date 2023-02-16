import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

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
                          getDirectMessagesCollectionReference(
                                  messagePathSplit[1])
                              .doc(messagePathSplit[2])
                              .snapshots()
                              .map((event) => event.data());
                    } else if (messagePathSplit.first ==
                        CoreConstants.groupMessageCollection) {
                      singleMessageDetails =
                          getGroupMessagesCollectionReference(
                                  messagePathSplit[1])
                              .doc(messagePathSplit[2])
                              .snapshots()
                              .map((event) => event.data());
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
    }
    await batch.commit();
  }
}
