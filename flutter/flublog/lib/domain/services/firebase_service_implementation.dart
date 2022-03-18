import 'package:firebase_analytics/firebase_analytics.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_crashlytics/firebase_crashlytics.dart';
import 'package:flublog/domain/domain.dart';
import 'package:flutter/material.dart';

/// An implementation for [FirebaseService].
class FirebaseServiceImplementation extends FirebaseService {
  @override
  Future<void> initializeFirebaseApp() async {
    await Firebase.initializeApp();
  }

  @override
  void implementFirebaseCrashlytics() async {
    await FirebaseCrashlytics.instance.setCrashlyticsCollectionEnabled(true);
    FlutterError.onError = FirebaseCrashlytics.instance.recordFlutterError;
  }

  @override
  List<NavigatorObserver> getFirebaseNavigatorObservers() => [
    FirebaseAnalyticsObserver(
      analytics: FirebaseAnalytics.instance,
    ),
  ];
}