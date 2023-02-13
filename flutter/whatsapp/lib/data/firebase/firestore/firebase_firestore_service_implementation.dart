import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/data/data.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService
    with
        FirestoreUserImplementation,
        FirestoreStatusImplementation,
        FirestoreDirectMessageService,
        FirestoreContactsService,
        FirestoreGroupMessageService,
        FirestoreCallsService,
        FirestoreSecurityDetailsService {
  factory FirebaseFirestoreServiceImplementation() => _instance;

  FirebaseFirestoreServiceImplementation._privateConstructor();

  static final FirebaseFirestoreServiceImplementation _instance =
      FirebaseFirestoreServiceImplementation._privateConstructor();
}
