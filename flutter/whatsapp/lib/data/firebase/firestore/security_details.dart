import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreSecurityDetailsService implements FirebaseFirestoreService {
  @override
  Future<void> createScreenshot(ScreenshotDetails screenshotDetails) async {
    await getScreenshotCollectionReference(screenshotDetails.userId)
        .add(screenshotDetails);
    NetworkListeners.securityDocumentWriteSizeStream
        .add(screenshotDetails.calculateSize);
  }

  @override
  Future<void> createMessageCopyForwardedSaved(
    MessageCopyForwardSavedDetails messageCopyForwardDetails,
  ) async {
    await getMessageCopyForwardSavedCollectionReference(
            messageCopyForwardDetails)
        .add(messageCopyForwardDetails);
    NetworkListeners.securityDocumentWriteSizeStream
        .add(messageCopyForwardDetails.calculateSize);
  }
}
