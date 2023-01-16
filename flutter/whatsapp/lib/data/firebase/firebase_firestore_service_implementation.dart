import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/models/user.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService {
  factory FirebaseFirestoreServiceImplementation() => _instance;

  FirebaseFirestoreServiceImplementation._privateConstructor();

  static final FirebaseFirestoreServiceImplementation _instance =
      FirebaseFirestoreServiceImplementation._privateConstructor();

  DocumentReference<UserDetails> _getUserFirestoreReferenceWithReference(
          String userId) =>
      firestore
          .collection(CoreConstants.userCollection)
          .doc(userId)
          .withConverter(
            fromFirestore: UserDetails.fromFirestore,
            toFirestore: (UserDetails userDetails, _) =>
                userDetails.toFirestore(),
          );

  @override
  StreamController<UserDetails?> getUserDetails(String userId) {
    var userDetails = StreamController<UserDetails?>();
    final userRef = _getUserFirestoreReferenceWithReference(userId);
    userRef.snapshots().listen(
      (event) {
        userDetails.add(event.data());
      },
    );
    return userDetails;
  }

  @override
  Future<void> setUserDetails(String userId, UserDetails userDetails) async {
    final userRef = _getUserFirestoreReferenceWithReference(userId);
    await userRef.set(userDetails);
  }
}
