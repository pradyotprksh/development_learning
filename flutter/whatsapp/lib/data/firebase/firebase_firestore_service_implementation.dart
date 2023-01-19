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

  @override
  StreamController<List<UserWithSingleStatusDetails>?> getStatus() {
    var status = StreamController<List<UserWithSingleStatusDetails>?>();
    getStatusCollectionReference()
        .orderBy(
          UserDetailsKey.createdOnTimeStamp,
          descending: true,
        )
        .snapshots()
        .listen(
      (event) {
        var statusWithUserDetails = <UserWithSingleStatusDetails>[];
        for (var element in event.docs) {
          final statusDetails = element.data();
          final userId = statusDetails.userId;

          final isUserPresent = statusWithUserDetails.where(
            (element) => element.userId == userId,
          );
          late StreamController<UserDetails?> userDetails;
          if (isUserPresent.isNotEmpty) {
            final isStatusPresent = isUserPresent.first.statusDetails.where(
              (element) => element.statusId == statusDetails.statusId,
            );
            if (isStatusPresent.isNotEmpty) {
              continue;
            }
            userDetails = isUserPresent.first.userDetails;
          } else {
            userDetails = getUserDetails(userId);
          }

          final allStatus = event.docs
              .where(
                (element) => element.data().userId == userId,
              )
              .map((e) => e.data())
              .toList();

          statusWithUserDetails.add(
            UserWithSingleStatusDetails(
              userId,
              allStatus,
              userDetails,
            ),
          );
        }
        status.add(statusWithUserDetails);
      },
    );
    return status;
  }
}
