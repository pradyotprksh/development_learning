import 'package:flutter/material.dart';

/// A service which will handle all the operations related to Firebase.
/// Like initializing the Firebase and different library related to Firebase.
abstract class FirebaseService {
  /// Initialize the Firebase application.
  Future<void> initializeFirebaseApp();

  /// Implement the Firebase crashlytics.
  void implementFirebaseCrashlytics();

  /// Get the navigator observer for navigation, like FirebaseAnalyticsObserver
  List<NavigatorObserver> getFirebaseNavigatorObservers();
}