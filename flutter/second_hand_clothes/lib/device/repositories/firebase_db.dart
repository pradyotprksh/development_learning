import 'package:second_hand_clothes/device/device.dart';
import 'package:second_hand_clothes/domain/domain.dart';
import 'package:second_hand_clothes/second_hand_clothes.dart';

/// A repository class for firebase realtime database,
/// this will implement the firebase db service of the data layer.
class RepositoriesDeviceFirebaseDB extends ServicesDeviceFirebaseDB {
  factory RepositoriesDeviceFirebaseDB() => _instance;

  RepositoriesDeviceFirebaseDB._privateConstructor();

  static final RepositoriesDeviceFirebaseDB _instance =
      RepositoriesDeviceFirebaseDB._privateConstructor();

  @override
  Future<String> getStringFormDetails(String formId) async {
    var formJson = '';
    if (formId == Constants().loginFormId) {
      formJson = await DeviceUtility().getJSONData(
        Assets().loginForm,
      );
    } else if (formId == Constants().signUpFormId) {
      formJson = await DeviceUtility().getJSONData(
        Assets().signUpForm,
      );
    }
    return formJson;
  }

  @override
  Future<FormData> getFormDetails(String formId) {
    throw UnimplementedError();
  }

  @override
  Stream<String?> getStringCurrentUserDetails(String userId) {
    throw UnimplementedError();
  }

  @override
  Stream<UserDetails?> getCurrentUserDetails() {
    throw UnimplementedError();
  }

  @override
  void dispose() {}
}
