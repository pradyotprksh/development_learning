import 'dart:async';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class FirebaseFirestoreService {
  final firestore = FirebaseFirestore.instance;

  StreamController<UserDetails?> getUserDetails(String userId);

  Future<void> setUserDetails(String userId, UserDetails userDetails);
}
