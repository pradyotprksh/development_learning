import 'dart:async';

import 'package:second_hand_clothes/app_details.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/device/device.dart';
import 'package:second_hand_clothes/domain/models/form_data.dart';
import 'package:second_hand_clothes/domain/models/user_details.dart';
import 'package:second_hand_clothes/domain/services/firebase_db.dart';

/// A firebase realtime data base repository which is child of
/// [ServicesFirebaseDB].
///
/// This will implement all the required methods and perform
/// the required tasks.
class RepositoriesFirebaseDB extends ServicesFirebaseDB {
  factory RepositoriesFirebaseDB() => _instance;

  RepositoriesFirebaseDB._privateConstructor()
      : _dataFirebaseDB = RepositoriesDataFirebaseDB(),
        _deviceFirebaseDB = RepositoriesDeviceFirebaseDB();

  static final RepositoriesFirebaseDB _instance =
      RepositoriesFirebaseDB._privateConstructor();

  final ServicesDataFirebaseDB _dataFirebaseDB;
  final ServicesDeviceFirebaseDB _deviceFirebaseDB;

  final _currentUserDetails = StreamController<UserDetails?>.broadcast();

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

  @override
  void dispose() {
    _currentUserDetails.close();
    _dataFirebaseDB.dispose();
    _deviceFirebaseDB.dispose();
  }

  @override
  Stream<String?> getStringCurrentUserDetails(String userId) {
    throw UnimplementedError();
  }

  @override
  Stream<UserDetails?> getCurrentUserDetails() {
    _dataFirebaseDB.getStringCurrentUserDetails('userId').listen(
      (details) {
        if (details != null) {
          _currentUserDetails.add(userDetailsFromJson(details));
        } else {
          _currentUserDetails.add(null);
        }
      },
    );

    return _currentUserDetails.stream;
  }
}
