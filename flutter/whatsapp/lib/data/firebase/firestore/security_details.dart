import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreSecurityDetailsService implements FirebaseFirestoreService {
  @override
  Future<void> createScreenshot(ScreenshotDetails screenshotDetails) async {
    await getScreenshotCollectionReference(screenshotDetails.userId)
        .add(screenshotDetails);
  }
}
