import 'dart:async';
import 'dart:convert';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_database/firebase_database.dart';
import 'package:second_hand_clothes/data/data.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// A repository class for firebase realtime database,
/// this will implement the firebase db service of the data layer.
class RepositoriesDataFirebaseDB extends ServicesDataFirebaseDB {
  factory RepositoriesDataFirebaseDB() => _instance;

  RepositoriesDataFirebaseDB._privateConstructor();

  static final RepositoriesDataFirebaseDB _instance =
      RepositoriesDataFirebaseDB._privateConstructor();

  final _currentUserDetails = StreamController<String?>.broadcast();

  @override
  Future<String> getStringFormDetails(String formId) async {
    final snapshot = await FirebaseDatabase.instance.ref(formId).get();
    if (snapshot.exists) {
      return jsonEncode(snapshot.value);
    }
    return '';
  }

  @override
  Future<FormData> getFormDetails(String formId) {
    throw UnimplementedError();
  }

  @override
  Stream<String?> getStringCurrentUserDetails(String userId) {
    FirebaseFirestore.instance
        .collection(UtilsDataConstants().firestoreCollectionUsers)
        .doc(userId)
        .snapshots()
        .listen(
      (event) {
        if (event.exists) {
          _currentUserDetails.add(jsonEncode(event.data()));
        } else {
          _currentUserDetails.add(null);
        }
      },
    );

    return _currentUserDetails.stream;
  }

  @override
  Stream<UserDetails?> getCurrentUserDetails() {
    throw UnimplementedError();
  }

  @override
  void dispose() {
    _currentUserDetails.close();
  }
}
