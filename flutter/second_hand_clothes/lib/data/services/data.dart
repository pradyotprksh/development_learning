import 'package:second_hand_clothes/domain/domain.dart';

/// A firebase auth service by the data layer which extends the domain
/// layer firebase auth service to implement the functionality related
/// to firebase auth in the data layer.
abstract class ServicesDataFirebaseAuth extends ServicesFirebaseAuth {
  const ServicesDataFirebaseAuth();
}

/// A firebase db service by the data layer extends the domain layer firebase
/// realtime database service to implement the functionality related to
/// firebase realtime database in the data layer.
abstract class ServicesDataFirebaseDB extends ServicesFirebaseDB {
  const ServicesDataFirebaseDB();
}