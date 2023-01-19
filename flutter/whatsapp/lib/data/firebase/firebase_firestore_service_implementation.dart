import 'dart:async';

import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService {
  factory FirebaseFirestoreServiceImplementation() => _instance;

  FirebaseFirestoreServiceImplementation._privateConstructor();

  static final FirebaseFirestoreServiceImplementation _instance =
      FirebaseFirestoreServiceImplementation._privateConstructor();

  @override
  StreamController<UserDetails?> getUserDetails(String userId) {
    var userDetails = StreamController<UserDetails?>();
    final userRef = getUserCollectionReference().doc(userId);
    userRef.snapshots().listen(
      (event) {
        userDetails.add(event.data());
      },
    );
    return userDetails;
  }

  @override
  Future<void> setUserDetails(String userId, UserDetails userDetails) async {
    final userRef = getUserCollectionReference().doc(userId);
    await userRef.set(userDetails);
  }

  @override
  Future<UserDetails?> getUserAccountByEmailAddress(String emailAddress) async {
    try {
      final usersCollection = await getUserCollectionReference()
          .where(
            UserDetailsKey.emailId,
            isEqualTo: emailAddress.replaceAll(' ', ''),
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
      final usersCollection = await getUserCollectionReference()
          .where(
            UserDetailsKey.phoneNumber,
            isEqualTo: phoneNumber.replaceAll(' ', ''),
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
    await getStatusCollectionReference().add(statusDetails);
  }
}
