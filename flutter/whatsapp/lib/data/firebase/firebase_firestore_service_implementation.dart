import 'dart:async';

import 'package:get/get_rx/get_rx.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

class FirebaseFirestoreServiceImplementation extends FirebaseFirestoreService {
  factory FirebaseFirestoreServiceImplementation() => _instance;

  FirebaseFirestoreServiceImplementation._privateConstructor();

  static final FirebaseFirestoreServiceImplementation _instance =
      FirebaseFirestoreServiceImplementation._privateConstructor();

  @override
  Rx<UserDetails?> getUserDetails(String userId) {
    var userDetails = Rx<UserDetails?>(null);
    final userRef = getUserCollectionReference().doc(userId);
    userRef.snapshots().listen(
      (event) {
        userDetails.value = event.data();
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
      FirebaseUtils.recordFlutterError(e);
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
      FirebaseUtils.recordFlutterError(e);
      return null;
    }
  }

  @override
  Future<void> setStatus(StatusDetails statusDetails) async {
    await getStatusCollectionReference().add(statusDetails);
  }

  @override
  Rx<List<UserWithSingleStatusDetails>?> getStatus(String currentUserId) {
    var status = Rx<List<UserWithSingleStatusDetails>?>(List.empty());
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
          var userDetails = Rx<UserDetails?>(null);
          if (isUserPresent.isNotEmpty) {
            final isStatusPresent =
                isUserPresent.first.statusWithSeenDetails.where(
              (element) =>
                  element.statusDetails.statusId == statusDetails.statusId,
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
              .map(
            (e) {
              var statusSeen = RxList<StatusSeenDetails>();
              var isSeen = RxBool(false);
              getStatusSeenCollectionReference(statusDetails.statusId)
                  .orderBy(StatusKey.seenOnTimeStamp, descending: true)
                  .snapshots()
                  .listen(
                (event) {
                  statusSeen.addAll(
                    event.docs.map(
                      (e) {
                        final data = e.data();
                        isSeen.value = data.userId == currentUserId;
                        return data;
                      },
                    ),
                  );
                },
              );
              return StatusWithSeenDetails(e.data(), statusSeen, isSeen);
            },
          ).toList();

          statusWithUserDetails.add(
            UserWithSingleStatusDetails(
              userId,
              allStatus,
              userDetails,
            ),
          );
        }
        status.value = statusWithUserDetails;
      },
    );
    return status;
  }

  @override
  Future<void> setStatusSeen(
    String statusId,
    StatusSeenDetails statusSeenDetails,
  ) async {
    final isAlreadyAvailable = await getStatusSeenCollectionReference(statusId)
        .doc(statusSeenDetails.userId)
        .get();
    if (!isAlreadyAvailable.exists) {
      await getStatusSeenCollectionReference(statusId)
          .doc(statusSeenDetails.userId)
          .set(statusSeenDetails);
    }
  }
}
