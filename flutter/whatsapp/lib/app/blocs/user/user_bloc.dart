import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  UserBloc(
    this._firebaseFirestoreService,
  ) : super(FetchingUserDetails()) {
    on<FetchUserDetails>(_fetchUserDetailsEvent);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;

  void _fetchUserDetailsEvent(
    FetchUserDetails event,
    Emitter<UserState> emit,
  ) {}
}
