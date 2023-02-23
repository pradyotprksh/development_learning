import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:collection/collection.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/device/device.dart';
import 'package:whatsapp/application/domain/domain.dart';

mixin FirestoreUserImplementation implements FirebaseFirestoreService {
  @override
  Stream<UserDetails?> getUserDetails(String userId) =>
      getUserCollectionReference().doc(userId).snapshots().map(
        (event) {
          final data = event.data();
          NetworkListeners.listener.add(
            Listener(
              ListenersFor.user,
              ListenersType.read,
              data?.size ?? 0,
            ),
          );
          return data;
        },
      );

  @override
  Future<void> setUserDetails(String userId, UserDetails userDetails) async {
    final userRef = getUserCollectionReference().doc(userId);
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.user,
        ListenersType.write,
        userDetails.calculateSize,
      ),
    );
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
      final data = usersCollection.docs.firstOrNull?.data();
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.user,
          ListenersType.read,
          (data?.size ?? 0).toDouble(),
        ),
      );
      return data;
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
      final data = usersCollection.docs.firstOrNull?.data();
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.user,
          ListenersType.read,
          (data?.size ?? 0).toDouble(),
        ),
      );
      return data;
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
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.user,
          ListenersType.write,
          loginHistoryDetails.calculateSize,
        ),
      );
    }
  }

  @override
  Future<void> updateUserDetails(
    String userId,
    Map<String, Object> values,
  ) async {
    final userRef = getUserCollectionReference().doc(userId);
    final details = {
      ...values,
      FirestoreItemKey.updatedOnTimeStamp:
          DeviceUtilsMethods.getCurrentTimeStamp(),
    };
    await userRef.update(
      details,
    );
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.user,
        ListenersType.write,
        details.getDocumentSize().toDouble(),
      ),
    );
  }

  @override
  Stream<List<UserDetails>> getUsersDetails(List<String> userIds) =>
      getUserCollectionReference()
          .where(FirestoreItemKey.userId, whereIn: userIds)
          .snapshots()
          .map(
            (event) => event.docs.map(
              (e) {
                final data = e.data();
                NetworkListeners.listener.add(
                  Listener(
                    ListenersFor.user,
                    ListenersType.read,
                    (data.size).toDouble(),
                  ),
                );
                return data;
              },
            ).toList(),
          );
}
