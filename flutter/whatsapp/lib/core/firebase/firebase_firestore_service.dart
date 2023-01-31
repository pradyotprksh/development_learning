import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class FirebaseFirestoreService {
  final firestore = FirebaseFirestore.instance;

  StreamController<DirectMessageDetails?> getMessageDetails(
    String currentUserId,
    String selectedUserId,
  );

  StreamController<UserDetails?> getUserDetails(String userId);

  StreamController<List<UserWithSingleStatusDetails>?> getStatus();

  DocumentReference<UserDetails> getUserDocumentReference(String userId);

  Future<void> setUserDetails(String userId, UserDetails userDetails);

  Future<void> setStatus(StatusDetails statusDetails);

  Future<void> createDirectMessage(DirectMessageDetails directMessageDetails);

  Future<void> setStatusSeen(
    String statusId,
    StatusSeenDetails statusSeenDetails,
  );

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

  CollectionReference<DirectMessageDetails>
      getDirectMessageCollectionReference() => _getCollectionReference(
            CoreConstants.directMessageCollection,
            DirectMessageDetails.fromFirestore,
            (DirectMessageDetails directMessageDetails, _) =>
                directMessageDetails.toFirestore(),
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

  CollectionReference<StatusSeenDetails> getStatusSeenCollectionReference(
    String statusId,
  ) =>
      _getCollectionReference<StatusSeenDetails>(
        CoreConstants.statusSeenCollection.replaceAll(
          CoreConstants.statusIdPlaceholder,
          statusId,
        ),
        StatusSeenDetails.fromFirestore,
        (StatusSeenDetails statusSeenDetails, _) =>
            statusSeenDetails.toFirestore(),
      );
}
