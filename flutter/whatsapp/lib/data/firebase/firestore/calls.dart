import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirebaseCallsService implements FirebaseFirestoreService {
  @override
  Future<void> createCall(CallDetails callDetails) async {
    for (var userId in callDetails.usersId) {
      await getCallDetailsCollectionReference(userId).add(callDetails);
    }
  }

  @override
  Stream<List<UserGroupCallDetails>> getCurrentUserCalls(String userId) =>
      getCallDetailsCollectionReference(userId)
          .orderBy(FirestoreItemKey.createdOnTimeStamp, descending: true)
          .snapshots()
          .map(
            (event) => event.docs.map(
              (e) {
                final callDetails = e.data();
                Stream<UsersGroupMessageDetails?>? groupMessageDetails;
                Stream<List<UserDetails>?>? userDetails;

                if (callDetails.isGroupCall) {
                  final groupId = callDetails.groupId;
                  if (groupId != null) {
                    groupMessageDetails =
                        getGroupMessageWithUsersDetails(groupId);
                  }
                } else {
                  userDetails = getUsersDetails(
                    callDetails.usersId,
                  );
                }

                return UserGroupCallDetails(
                  callDetails,
                  groupMessageDetails,
                  userDetails,
                );
              },
            ).toList(),
          );

  @override
  Future<void> clearCallLogs(String userId) async {
    final batch = firestore.batch();
    final callLogs = await getCallDetailsCollectionReference(userId).get();
    for (var call in callLogs.docs) {
      batch.delete(call.reference);
    }
    await batch.commit();
  }
}
