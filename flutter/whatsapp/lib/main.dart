import 'package:fast_cached_network_image/fast_cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:path_provider/path_provider.dart' as path_provider;
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/data/data.dart';
import 'package:whatsapp/device/device.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await _initialSetups();

  runApp(
    MultiBlocProvider(
      providers: [
        BlocProvider(
          create: (_) => UtilitiesBloc(
            FirebaseAuthServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
          )..add(
              const InitiateConnectivityCheck(),
            ),
        ),
        BlocProvider(
          create: (_) => NetworkBloc()
            ..add(
              const StartAllSizeListenersEvent(),
            ),
        ),
        BlocProvider(
          create: (_) => ThemeBloc()
            ..add(
              const FetchCurrentThemeEvent(),
            ),
        ),
        BlocProvider(
          create: (_) => AuthenticationBloc(
            FirebaseAuthServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
          )..add(
              const CheckForRemoteConfigs(),
            ),
        ),
        BlocProvider(
          create: (_) => UserBloc(
            FirebaseFirestoreServiceImplementation(),
            FirebaseAuthServiceImplementation(),
            FirebaseStorageServiceImplementation(),
            DeviceDetailsImplementation(),
          ),
        ),
        BlocProvider(
          create: (_) => LocalizationsBloc()
            ..add(
              const FetchCurrentLocalizationEvent(),
            ),
        ),
      ],
      child: const WhatsappApp(),
    ),
  );
}

Future blocSetup() async {
  Bloc.observer = WhatsAppBlocObserver();
  await localStorageSetup();
}

Future localStorageSetup() async {
  await Hive.initFlutter();
  HydratedBloc.storage = await HydratedStorage.build(
    storageDirectory: AppDetails.isWeb
        ? HydratedStorage.webStorageDirectory
        : await path_provider.getApplicationDocumentsDirectory(),
  );
}

Future _initialSetups() async {
  await FirebaseUtils.initiation();
  await FirebaseUtils.appCheckInitiation();
  await FirebaseUtils.crashlyticsInitiation();
  await FirebaseUtils.remoteConfigInitiation();

  await blocSetup();

  await DeviceCameras.getCameras();

  if (AppDetails.isWeb) {
    await FastCachedImageConfig.init(
      clearCacheAfter: const Duration(
        days: 1,
      ),
    );
  }
}
