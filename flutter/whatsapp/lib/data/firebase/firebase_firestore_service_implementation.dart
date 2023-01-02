import 'dart:async';

import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/models/user.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService {
  @override
  StreamController<UserDetails?> getUserDetails(String userId) {
    var userDetails = StreamController<UserDetails?>();
    final userRef = firestore
        .collection(Constants.userCollection)
        .doc(userId)
        .withConverter(
          fromFirestore: UserDetails.fromFirestore,
          toFirestore: (UserDetails userDetails, _) =>
              userDetails.toFirestore(),
        );
    userRef.snapshots().listen(
      (event) {
        userDetails.add(event.data());
      },
    );
    return userDetails;
  }
}
