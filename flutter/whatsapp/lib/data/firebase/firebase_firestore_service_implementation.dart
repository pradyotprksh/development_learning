import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService {
  factory FirebaseFirestoreServiceImplementation() => _instance;

  FirebaseFirestoreServiceImplementation._privateConstructor();

  static final FirebaseFirestoreServiceImplementation _instance =
      FirebaseFirestoreServiceImplementation._privateConstructor();

  CollectionReference<Map<String, dynamic>> _getCollectionReference(
    String collectionPath,
  ) =>
      firestore.collection(collectionPath);

  DocumentReference<UserDetails> _getUserFirestoreReferenceWithReference(
    String userId,
  ) =>
      _getCollectionReference(CoreConstants.userCollection)
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

  @override
  Future<UserDetails?> getUserAccountByEmailAddress(String emailAddress) async {
    try {
      final usersCollection =
          await _getCollectionReference(CoreConstants.userCollection)
              .where(
                UserDetailsKey.emailId,
                isEqualTo: emailAddress.replaceAll(' ', ''),
              )
              .withConverter(
                fromFirestore: UserDetails.fromFirestore,
                toFirestore: (UserDetails userDetails, _) =>
                    userDetails.toFirestore(),
              )
              .limit(1)
              .get();
      return usersCollection.docs.first.data();
    } catch (e) {
      return null;
    }
  }

  @override
  Future<UserDetails?> getUserAccountByPhoneNumber(String phoneNumber) async {
    try {
      final usersCollection =
          await _getCollectionReference(CoreConstants.userCollection)
              .where(
                UserDetailsKey.phoneNumber,
                isEqualTo: phoneNumber.replaceAll(' ', ''),
              )
              .withConverter(
                fromFirestore: UserDetails.fromFirestore,
                toFirestore: (UserDetails userDetails, _) =>
                    userDetails.toFirestore(),
              )
              .limit(1)
              .get();
      return usersCollection.docs.first.data();
    } catch (e) {
      return null;
    }
  }

  @override
  Future<void> setStatus(StatusDetails statusDetails) async {
    await firestore
        .collection(CoreConstants.statusCollection)
        .withConverter(
          fromFirestore: StatusDetails.fromFirestore,
          toFirestore: (StatusDetails statusDetails, _) =>
              statusDetails.toFirestore(),
        )
        .add(statusDetails);
  }
}
