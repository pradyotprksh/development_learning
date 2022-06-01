/// A services class inside domain module, which contains a set of services
/// which are required throughout the application.
///
/// These services are mainly which are needed at any point of time and want
/// it to be up and running.
export 'firebase_auth.dart';
export 'firebase_core.dart';
export 'firebase_db.dart';

/// A common domain services class which will contain the common functionality
/// for the domain layer and also the intermediate layers.
abstract class DomainServices {
  const DomainServices();

  /// Dispose all the services at end.
  void dispose();
}