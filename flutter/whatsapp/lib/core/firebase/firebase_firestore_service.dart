import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class FirebaseFirestoreService {
  final firestore = FirebaseFirestore.instance;

  StreamController<UserDetails?> getUserDetails(String userId);

  StreamController<List<UserWithSingleStatusDetails>?> getStatus();

  Future<void> setUserDetails(String userId, UserDetails userDetails);

  Future<void> setStatus(StatusDetails statusDetails);

  Future<UserDetails?> getUserAccountByPhoneNumber(String phoneNumber);

  Future<UserDetails?> getUserAccountByEmailAddress(String emailAddress);

  CollectionReference<R> _getCollectionReference<R>(
    String collectionPath,
    FromFirestore<R> fromFirestore,
    ToFirestore<R> toFirestore,
  ) =>
      firestore.collection(collectionPath).withConverter(
            fromFirestore: fromFirestore,
            toFirestore: toFirestore,
          );

  CollectionReference<UserDetails> getUserCollectionReference() =>
      _getCollectionReference<UserDetails>(
        CoreConstants.userCollection,
        UserDetails.fromFirestore,
        (UserDetails userDetails, _) => userDetails.toFirestore(),
      );

  CollectionReference<StatusDetails> getStatusCollectionReference() =>
      _getCollectionReference<StatusDetails>(
        CoreConstants.statusCollection,
        StatusDetails.fromFirestore,
        (StatusDetails statusDetails, _) => statusDetails.toFirestore(),
      );
}
