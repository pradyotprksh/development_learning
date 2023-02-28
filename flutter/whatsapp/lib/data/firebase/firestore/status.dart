import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreStatusImplementation implements FirebaseFirestoreService {
  @override
  Future<void> setStatus(StatusDetails statusDetails) async {
    await getStatusCollectionReference().add(statusDetails);
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.status,
        ListenersType.write,
        statusDetails.calculateSize,
      ),
    );
  }

  @override
  Stream<List<UserWithSingleStatusDetails>?> getStatus() =>
      getStatusCollectionReference()
          .orderBy(
            FirestoreItemKey.createdOnTimeStamp,
            descending: true,
          )
          .snapshots()
          .map(
        (event) {
          var statusWithUserDetails = <UserWithSingleStatusDetails>[];
          for (var element in event.docs) {
            final statusDetails = element.data();
            final userId = statusDetails.userId;

            final isUserPresent = statusWithUserDetails.where(
              (element) => element.userId == userId,
            );
            if (isUserPresent.isNotEmpty) {
              final isStatusPresent = isUserPresent.first.statusDetails.where(
                (element) => element.statusId == statusDetails.statusId,
              );
              if (isStatusPresent.isNotEmpty) {
                continue;
              }
            }

            final allStatus = event.docs
                .where(
                  (element) => element.data().userId == userId,
                )
                .map((e) => e.data())
                .toList();

            for (var element in allStatus) {
              NetworkListeners.listener.add(
                Listener(
                  ListenersFor.status,
                  ListenersType.read,
                  element.size,
                ),
              );
            }

            statusWithUserDetails.add(
              UserWithSingleStatusDetails(
                userId,
                allStatus,
                getUserDetails(userId),
              ),
            );
          }
          return statusWithUserDetails;
        },
      );

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
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.status,
          ListenersType.write,
          statusSeenDetails.calculateSize,
        ),
      );
    }
  }

  @override
  Future<List<String>> deleteStatusOnTimeCompletion(String userId) async {
    var storageReference = <String>[];
    final allCurrentUserStatus = await getStatusCollectionReference().get();
    final batch = firestore.batch();
    for (var status in allCurrentUserStatus.docs) {
      final statusTime = status.data().createdOnTimeStamp;
      if (DeviceUtilsMethods.getTimeDifferenceInHrs(statusTime) >=
          FirebaseRemoteConfigService.statusDeleteTimeValue()) {
        if (status.data().firestoreFilePath != null) {
          storageReference.add(status.data().firestoreFilePath ?? '');
        }
        batch.delete(status.reference);
        NetworkListeners.listener.add(
          Listener(
            ListenersFor.status,
            ListenersType.write,
            status.data().size,
          ),
        );
      }
    }
    await batch.commit();
    storageReference.removeWhere((element) => element.isEmpty);
    return storageReference;
  }
}
