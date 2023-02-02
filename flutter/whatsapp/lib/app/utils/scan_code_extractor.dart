abstract class ScanCodeResult {
  const ScanCodeResult();
}

class UserScanResult extends ScanCodeResult {
  const UserScanResult(this.userId);
  final String userId;
}

class NoneResult extends ScanCodeResult {
  const NoneResult();
}

abstract class ScanCodeExtractor {
  static ScanCodeResult getScanCodeDetails(String? data) {
    if (data != null) {
      if (data.contains('user')) {
        final userId = data.split('/');
        if (userId.length == 2) {
          return UserScanResult(userId.last);
        }
      }
    }
    return const NoneResult();
  }
}
