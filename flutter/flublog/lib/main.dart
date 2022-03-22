import 'package:flublog/app/app.dart';
import 'package:flublog/constants.dart';
import 'package:flublog/domain/domain.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

/// The entry point of the application
void main() async {
  try {
    WidgetsFlutterBinding.ensureInitialized();
    _initializeServices();
    await _startServices();
    runApp(const MyApp());
  } catch (error) {
    Utility.printELog(error.toString());
  }
}

/// Initializing all the services for the application.
void _initializeServices() {
  Get
    ..put<FirebaseService>(FirebaseServiceImplementation())
    ..put<DBService>(DBServiceImplementation());
}

/// Starting all the required services before application gets loaded.
Future<void> _startServices() async {
  await Get.find<FirebaseService>().initializeFirebaseApp();
  Get.find<FirebaseService>().implementFirebaseCrashlytics();
  await Get.putAsync<void>(() => Get.find<DBService>().initializeService());
}

/// Local log writer for the [GetMaterialApp].
///
/// [message] contains the details to be shown
///
/// [isError] tells if the message is a error or not.
void localLogWriter(String message, {bool isError = false}) {
  if (isError) {
    Utility.printELog(message);
  } else {
    Utility.printDLog(message);
  }
}

/// The my app class which will initialize the basic application structure.
class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    ApplicationDetails.setApplicationOrientation();

    return GetMaterialApp(
      title: Constants.appName,
      debugShowCheckedModeBanner:
          ApplicationDetails.isDebugMode || ApplicationDetails.isProfileMode,

      navigatorObservers:
          Get.find<FirebaseService>().getFirebaseNavigatorObservers(),

      themeMode: ThemeMode.system,
      // TODO: Change "material" from hardcoded to local fetch
      theme: ApplicationTheme.getTheme(ThemeMode.light, "material"),
      darkTheme: ApplicationTheme.getTheme(ThemeMode.dark, "material"),
      highContrastTheme: ApplicationTheme.getTheme(ThemeMode.light, "material"),
      highContrastDarkTheme:
          ApplicationTheme.getTheme(ThemeMode.dark, "material"),

      enableLog:
          ApplicationDetails.isDebugMode || ApplicationDetails.isProfileMode,
      logWriterCallback: localLogWriter,
    );
  }
}
