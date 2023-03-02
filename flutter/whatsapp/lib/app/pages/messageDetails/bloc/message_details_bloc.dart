import 'package:replay_bloc/replay_bloc.dart';
import 'package:whatsapp/app/app.dart';

class MessageDetailsBloc
    extends Bloc<MessageDetailsEvent, MessageDetailsState> {
  MessageDetailsBloc() : super(const MessageDetailsState()) {
    on<FetchDetails>(_getMessageDetails);
  }

  void _getMessageDetails(
    FetchDetails event,
    Emitter<MessageDetailsState> emit,
  ) {}
}
