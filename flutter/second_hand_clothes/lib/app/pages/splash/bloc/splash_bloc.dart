import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:second_hand_clothes/app/app.dart';

/// A bloc class for splash, which will listen to all the events related
/// to splash and update the state of the splash journey.
class SplashBloc extends Bloc<SplashEvent, SplashState> {
  SplashBloc() : super(const SplashState.unknown()) {
    on<AuthorizationSplashEvent>(_authorizationEvent);
  }

  /// Whenever [AuthorizationSplashEvent] is sent this method will be called.
  void _authorizationEvent(
    AuthorizationSplashEvent event,
    Emitter<SplashState> emit,
  ) {
    emit(const SplashState.unauthenticated());
  }
}
