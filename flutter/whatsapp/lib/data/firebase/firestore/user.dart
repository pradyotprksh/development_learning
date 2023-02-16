import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreUserImplementation implements FirebaseFirestoreService {
  @override
  Stream<UserDetails?> getUserDetails(String userId) =>
      getUserCollectionReference().doc(userId).snapshots().map(
            (event) => event.data(),
          );

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
            FirestoreItemKey.emailId,
            isEqualTo: emailAddress.replaceAll(' ', ''),
          )
          .limit(1)
          .get();
      return usersCollection.docs.first.data();
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return null;
    }
  }

  @override
  Future<UserDetails?> getUserAccountByPhoneNumber(String phoneNumber) async {
    try {
      final usersCollection = await getUserCollectionReference()
          .where(
            FirestoreItemKey.phoneNumber,
            isEqualTo: phoneNumber.replaceAll(' ', ''),
          )
          .limit(1)
          .get();
      return usersCollection.docs.first.data();
    } catch (e) {
      FirebaseUtils.recordFlutterError(e);
      return null;
    }
  }

  @override
  DocumentReference<UserDetails> getUserDocumentReference(String userId) =>
      getUserCollectionReference().doc(userId);

  @override
  Future<void> setUserLogInHistory(
    LoginHistoryDetails loginHistoryDetails,
  ) async {
    final isAvailable =
        await getLoginHistoryCollectionReference(loginHistoryDetails.userId)
            .doc(DeviceUtilsMethods.getCurrentDateWithCurrentHour())
            .get();
    if (!isAvailable.exists) {
      await getLoginHistoryCollectionReference(loginHistoryDetails.userId)
          .doc(DeviceUtilsMethods.getCurrentDateWithCurrentHour())
          .set(loginHistoryDetails);
    }
  }

  @override
  Future<void> updateUserDetails(
    String userId,
    Map<String, Object> values,
  ) async {
    final userRef = getUserCollectionReference().doc(userId);
    await userRef.update(
      {
        ...values,
        FirestoreItemKey.updatedOnTimeStamp:
            DeviceUtilsMethods.getCurrentTimeStamp(),
      },
    );
  }

  @override
  Stream<List<UserDetails>> getUsersDetails(List<String> userIds) =>
      getUserCollectionReference()
          .where(FirestoreItemKey.userId, whereIn: userIds)
          .snapshots()
          .map(
            (event) => event.docs.map((e) => e.data()).toList(),
          );
}
