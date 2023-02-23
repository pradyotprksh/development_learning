import 'dart:ui';

import 'package:firebase_app_check/firebase_app_check.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_crashlytics/firebase_crashlytics.dart';
import 'package:firebase_remote_config/firebase_remote_config.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/confidential/confidential.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/firebase_options.dart';

abstract class FirebaseUtils {
  static Future<void> initiation() async {
    await Firebase.initializeApp(
      options: DefaultFirebaseOptions.currentPlatform,
    );
  }

  static Future<void> appCheckInitiation() async {
    await FirebaseAppCheck.instance.activate(
      webRecaptchaSiteKey: ConfidentialKeys.sitekey,
      androidProvider: AndroidProvider.debug,
    );
  }

  static Future<void> crashlyticsInitiation() async {
    FlutterError.onError = FirebaseCrashlytics.instance.recordFlutterFatalError;
    PlatformDispatcher.instance.onError = (error, stack) {
      if (AppDetails.isReleaseMode) {
        FirebaseCrashlytics.instance.recordError(error, stack, fatal: true);
      }
      return true;
    };
  }

  static Future<void> remoteConfigInitiation() async {
    final remoteConfig = FirebaseRemoteConfig.instance;
    await remoteConfig.setConfigSettings(
      RemoteConfigSettings(
        fetchTimeout: const Duration(seconds: 10),
        minimumFetchInterval: const Duration(seconds: 10),
      ),
    );
    await remoteConfig.fetchAndActivate();
  }

  static void recordFlutterError(Object exception) {
    UtilsLogger.errorLog(exception);
    if (AppDetails.isReleaseMode) {
      FirebaseCrashlytics.instance.recordFlutterError(
        FlutterErrorDetails(
          exception: exception,
        ),
      );
    }
  }
}
