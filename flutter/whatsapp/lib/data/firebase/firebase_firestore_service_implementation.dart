import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/models/user.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService {
  @override
  Future<UserDetails?> getUserDetails(String userId) async {
    final userRef = firestore.collection('users').doc(userId).withConverter(
          fromFirestore: UserDetails.fromFirestore,
          toFirestore: (UserDetails userDetails, _) =>
              userDetails.toFirestore(),
        );
    final snapshot = await userRef.get();
    return snapshot.data();
  }
}
