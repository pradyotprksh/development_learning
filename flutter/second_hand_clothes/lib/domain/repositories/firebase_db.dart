import 'package:second_hand_clothes/app_details.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/data/repositories/firebase_db.dart';
import 'package:second_hand_clothes/device/device.dart';
import 'package:second_hand_clothes/device/repositories/firebase_db.dart';
import 'package:second_hand_clothes/domain/models/form_data.dart';
import 'package:second_hand_clothes/domain/services/firebase_db.dart';

/// A firebase realtime data base repository which is child of
/// [ServicesFirebaseDB].
///
/// This will implement all the required methods and perform
/// the required tasks.
class RepositoriesFirebaseDB extends ServicesFirebaseDB {
  RepositoriesFirebaseDB()
      : _dataFirebaseDB = RepositoriesDataFirebaseDB(),
        _deviceFirebaseDB = RepositoriesDeviceFirebaseDB();

  final ServicesDataFirebaseDB _dataFirebaseDB;
  final ServicesDeviceFirebaseDB _deviceFirebaseDB;

  @override
  Future<FormData> getFormDetails(String formId) async {
    var formStringData = '';
    if (UtilsAppDetails().isDebugMode && UtilsAppDetails().useMockResponses) {
      formStringData = await _deviceFirebaseDB.getStringFormDetails(formId);
    } else {
      try {
        formStringData = await _dataFirebaseDB.getStringFormDetails(formId);
      } catch (_) {
        formStringData = await _deviceFirebaseDB.getStringFormDetails(formId);
      }
    }
    return formDataFromJson(formStringData);
  }

  @override
  Future<String> getStringFormDetails(String formId) {
    throw UnimplementedError();
  }
}
