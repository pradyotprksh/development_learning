import 'package:equatable/equatable.dart';

/// An event class for splash bloc, this will be used to trigger an event from
/// splash page if required.
abstract class SplashEvent extends Equatable {
  @override
  List<Object?> get props => [];
}

/// A child of [SplashEvent] which will trigger the auth check
class AuthorizationSplashEvent extends SplashEvent {}
