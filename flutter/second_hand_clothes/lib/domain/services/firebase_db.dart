import 'package:second_hand_clothes/domain/domain.dart';

/// A service for firebase realtime database, which will connect to firebase
/// and get the details from the database.
///
/// Any read or write operation will be declared here and implemented some
/// where else.
abstract class ServicesFirebaseDB {
  const ServicesFirebaseDB();

  /// Get the form details with id [formId].
  Future<FormData> getFormDetails(String formId);

  /// Get the form details with id [formId], and data type will be string.
  Future<String> getStringFormDetails(String formId);
}