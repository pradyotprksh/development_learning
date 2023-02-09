import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class GroupMessageBloc extends Bloc<GroupMessageEvent, GroupMessageState> {
  GroupMessageBloc(
    this._firebaseFirestoreService,
  ) : super(const GroupMessageState()) {
    on<FetchGroupDetails>(_fetchGroupDetails);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;

  void _fetchGroupDetails(
    FetchGroupDetails event,
    Emitter<GroupMessageState> emit,
  ) async {
    await emit.forEach(
      _firebaseFirestoreService.getGroupMessageWithUsersDetails(event.groupId),
      onData: (details) => state.copyWith(
        groupMessageDetails: details,
      ),
    );
  }
}
