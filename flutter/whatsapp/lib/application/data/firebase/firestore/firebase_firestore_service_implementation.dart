import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/data/data.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService
    with
        FirestoreUserImplementation,
        FirestoreStatusImplementation,
        FirestoreDirectMessageService,
        FirestoreContactsService,
        FirestoreGroupMessageService,
        FirestoreCallsService,
        FirestoreSecurityDetailsService,
        FirestoreSavedMessagesService {
  factory FirebaseFirestoreServiceImplementation() => _instance;

  FirebaseFirestoreServiceImplementation._privateConstructor();

  static final FirebaseFirestoreServiceImplementation _instance =
      FirebaseFirestoreServiceImplementation._privateConstructor();
}
