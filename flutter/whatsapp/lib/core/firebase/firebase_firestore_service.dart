import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/domain/domain.dart';

abstract class FirebaseFirestoreService {
  final firestore = FirebaseFirestore.instance;

  Future<UserDetails?> getUserDetails(String userId);
}
