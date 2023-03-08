import 'package:replay_bloc/replay_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class MessageDetailsBloc
    extends Bloc<MessageDetailsEvent, MessageDetailsState> {
  MessageDetailsBloc(
    this._firebaseFirestoreService,
  ) : super(const MessageDetailsState()) {
    on<FetchDetails>(_getMessageDetails);
    on<GetDirectMessageDetails>(_getDirectMessageDetails);
    on<GetGroupMessageDetails>(_getGroupMessageDetails);
    on<FetchUsersDetails>(_fetchUserDetails);
    on<GetGroupMessageAttachments>(_getGroupMessageAttachments);
    on<GetDirectMessageAttachments>(_getDirectMessageAttachments);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;

  void _getMessageDetails(
    FetchDetails event,
    Emitter<MessageDetailsState> emit,
  ) {
    final directMessageId = event.messageRouteDetails.directMessageId;
    final groupId = event.messageRouteDetails.groupId;
    if (directMessageId != null) {
      add(GetDirectMessageDetails(directMessageId));
    } else if (groupId != null) {
      add(GetGroupMessageDetails(groupId));
    }
  }

  void _getDirectMessageDetails(
    GetDirectMessageDetails event,
    Emitter<MessageDetailsState> emit,
  ) async {
    await emit.forEach(
      _firebaseFirestoreService.getDirectMessageDetails(
        event.directMessageId,
      ),
      onData: (details) {
        if (details != null) {
          add(GetDirectMessageAttachments(event.directMessageId));
        }
        return state.copyWith(
          directMessageDetails: details,
        );
      },
    );
  }

  void _getGroupMessageDetails(
    GetGroupMessageDetails event,
    Emitter<MessageDetailsState> emit,
  ) async {
    await emit.forEach(
      _firebaseFirestoreService.getGroupMessageWithUsersDetails(
        event.groupMessageId,
      ),
      onData: (details) {
        if (details?.groupMessageDetails?.groupId != null) {
          add(const FetchUsersDetails());
          add(GetGroupMessageAttachments(event.groupMessageId));
        }
        return state.copyWith(
          groupMessageDetails: details,
        );
      },
    );
  }

  void _fetchUserDetails(
    FetchUsersDetails event,
    Emitter<MessageDetailsState> emit,
  ) async {
    final users = state.groupMessageDetails?.usersDetails;
    if (users != null) {
      await emit.forEach(
        users,
        onData: (usersDetails) => state.copyWith(
          usersDetails: usersDetails,
        ),
      );
    }
  }

  void _getGroupMessageAttachments(
    GetGroupMessageAttachments event,
    Emitter<MessageDetailsState> emit,
  ) async {
    await emit.forEach(
      _firebaseFirestoreService.getGroupMessagesMediaLinksDocs(
        event.groupMessageId,
      ),
      onData: (attachments) {
        attachments.removeWhere(
          (element) =>
              element.message.links().isEmpty &&
              (element.attachments == null ||
                  element.attachments?.isEmpty == true),
        );
        return state.copyWith(
          media: attachments,
        );
      },
    );
  }

  void _getDirectMessageAttachments(
    GetDirectMessageAttachments event,
    Emitter<MessageDetailsState> emit,
  ) async {
    await emit.forEach(
      _firebaseFirestoreService.getDirectMessagesMediaLinksDocs(
        event.directMessageId,
      ),
      onData: (attachments) {
        attachments.removeWhere(
          (element) =>
              element.message.links().isEmpty &&
              (element.attachments == null ||
                  element.attachments?.isEmpty == true),
        );
        return state.copyWith(
          media: attachments,
        );
      },
    );
  }
}
