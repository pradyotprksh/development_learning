import 'package:flutter/material.dart';

/// A firebase service for firebase core functionality.
///
/// Anything which is related to firebase core work will be setup from here.
abstract class ServicesFirebaseCore {
  /// Initialize the Firebase application.
  Future<void> initializeFirebaseApp();

  /// Implement the Firebase crashlytics.
  void implementFirebaseCrashlytics();

  /// Get the navigator observer for navigation, like FirebaseAnalyticsObserver
  List<NavigatorObserver> getFirebaseNavigatorObservers();
}