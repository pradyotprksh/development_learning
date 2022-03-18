import 'package:flublog/app/app.dart';
import 'package:flublog/domain/domain.dart';
import 'package:flutter/foundation.dart' as foundation;
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
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
  Get.put<FirebaseService>(FirebaseServiceImplementation());
}

/// Starting all the required services before application gets loaded.
Future<void> _startServices() async {
  await Get.find<FirebaseService>().initializeFirebaseApp();
  Get.find<FirebaseService>().implementFirebaseCrashlytics();
}

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
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);

    return GetMaterialApp(
      title: StringConstants.appName,
      debugShowCheckedModeBanner:
          foundation.kDebugMode || foundation.kProfileMode,
      navigatorObservers:
          Get.find<FirebaseService>().getFirebaseNavigatorObservers(),
      themeMode: ThemeMode.system,
      enableLog: foundation.kDebugMode || foundation.kProfileMode,
      logWriterCallback: localLogWriter,
    );
  }
}
