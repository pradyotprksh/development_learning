import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class StatusBloc extends Bloc<StatusEvent, StatusState> {
  StatusBloc() : super(const StatusState()) {
    on<FetchStatus>(_fetchStatusEvent);
  }

  void _fetchStatusEvent(
    FetchStatus event,
    Emitter<StatusState> emit,
  ) {}
}
