import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreCallsService implements FirebaseFirestoreService {
  @override
  Future<void> createCall(CallDetails callDetails) async {
    final batch = firestore.batch();
    for (var userId in callDetails.usersId) {
      batch.set(getCallDetailsCollectionReference(userId).doc(), callDetails);
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.calls,
          ListenersType.write,
          callDetails.calculateSize,
        ),
      );
    }
    await batch.commit();
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

                NetworkListeners.listener.add(
                  Listener(
                    ListenersFor.calls,
                    ListenersType.read,
                    callDetails.size,
                  ),
                );

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
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.calls,
          ListenersType.write,
          call.data().size,
        ),
      );
    }
    await batch.commit();
  }
}
