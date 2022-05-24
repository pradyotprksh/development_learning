import 'package:second_hand_clothes/domain/domain.dart';

/// A firebase db service by the data layer extends the domain layer firebase
/// realtime database service to implement the functionality related to
/// firebase realtime database in the data layer.
abstract class ServicesDeviceFirebaseDB extends ServicesFirebaseDB {
  const ServicesDeviceFirebaseDB();
}