import 'package:firebase_analytics/firebase_analytics.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_crashlytics/firebase_crashlytics.dart';
import 'package:flutter/material.dart';
import 'package:second_hand_clothes/domain/domain.dart';

/// An implementation class for [ServicesFirebaseCore], which will implements
/// all the functionality of it.
class FirebaseCore extends ServicesFirebaseCore {
  factory FirebaseCore() => _instance;

  FirebaseCore._privateConstructor();

  static final FirebaseCore _instance = FirebaseCore._privateConstructor();

  @override
  List<NavigatorObserver> getFirebaseNavigatorObservers() => [
        FirebaseAnalyticsObserver(
          analytics: FirebaseAnalytics.instance,
        ),
      ];

  @override
  void implementFirebaseCrashlytics() async {
    await FirebaseCrashlytics.instance.setCrashlyticsCollectionEnabled(true);
    FlutterError.onError = FirebaseCrashlytics.instance.recordFlutterError;
  }

  @override
  Future<void> initializeFirebaseApp() async {
    await Firebase.initializeApp();
  }

  @override
  void dispose() {}
}
