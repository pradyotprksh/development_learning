import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class UserDetailsBloc extends Bloc<UserDetailsEvent, UserDetailsState> {
  UserDetailsBloc(this._firebaseAuthService) : super(const UserDetailsState()) {
    on<FetchFirebaseUserDetails>(_fetchUserDetailsFromFirebase);
    on<UserDetailsFormEvent>(_userDetailsFormEvent);
  }

  final FirebaseAuthService _firebaseAuthService;

  void _fetchUserDetailsFromFirebase(
    FetchFirebaseUserDetails event,
    Emitter<UserDetailsState> emit,
  ) {
    final userDetails = _firebaseAuthService.getUserDetails();
    if (userDetails != null) {
      final isEmailAddressAvailable = userDetails.email != '';
      final isPhoneNumberAvailable = userDetails.phoneNumber != '';

      emit(
        UserDetailsState(
          username: state.username,
          emailAddress: userDetails.email ?? state.emailAddress,
          phoneNumber: userDetails.phoneNumber ?? state.phoneNumber,
          isEmailAddressAvailable: isEmailAddressAvailable,
          isPhoneNumberAvailable: isPhoneNumberAvailable,
        ),
      );
    }
  }

  void _userDetailsFormEvent(
    UserDetailsFormEvent event,
    Emitter<UserDetailsState> emit,
  ) {}
}
