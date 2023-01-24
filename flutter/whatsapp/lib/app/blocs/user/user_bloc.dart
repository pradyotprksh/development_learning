import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  UserBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
  ) : super(const FetchingUserDetails()) {
    on<FetchUserDetails>(_fetchUserDetailsEvent);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;

  Future<void> _fetchUserDetailsEvent(
    FetchUserDetails event,
    Emitter<UserState> emit,
  ) async {
    emit(const FetchingUserDetails());
    var userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      emit(
        UserDetailsAvailable(
          _firebaseFirestoreService.getUserDetails(userId),
        ),
      );
    }
  }
}
