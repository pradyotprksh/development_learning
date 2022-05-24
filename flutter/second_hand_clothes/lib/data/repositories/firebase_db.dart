import 'package:firebase_database/firebase_database.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A repository class for firebase realtime database,
/// this will implement the firebase db service of the data layer.
class RepositoriesDataFirebaseDB extends ServicesDataFirebaseDB {
  @override
  Future<String> getStringFormDetails(String formId) async {
    final snapshot = await FirebaseDatabase.instance.ref(formId).get();
    if (snapshot.exists) {
      return snapshot.value.toString();
    }
    return '';
  }

  @override
  Future<FormData> getFormDetails(String formId) {
    throw UnimplementedError();
  }
}
