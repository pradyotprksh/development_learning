import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreSecurityDetailsService implements FirebaseFirestoreService {
  @override
  Future<void> createScreenshot(ScreenshotDetails screenshotDetails) async {
    await getScreenshotCollectionReference(screenshotDetails.userId)
        .add(screenshotDetails);
  }

  @override
  Future<void> createMessageCopyForwarded(
    MessageCopyForwardDetails messageCopyForwardDetails,
  ) async {
    await getMessageCopyForwardCollectionReference(
            messageCopyForwardDetails.userId)
        .add(messageCopyForwardDetails);
  }
}
