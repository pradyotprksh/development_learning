import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:path_provider/path_provider.dart' as path_provider;
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/data/data.dart';
import 'package:whatsapp/application/device/device.dart';

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
  HydratedBloc.storage = await HydratedStorage.build(
    storageDirectory: await path_provider.getApplicationDocumentsDirectory(),
  );
}

Future _initialSetups() async {
  await FirebaseUtils.initiation();
  await FirebaseUtils.appCheckInitiation();
  await FirebaseUtils.crashlyticsInitiation();
  await FirebaseUtils.remoteConfigInitiation();

  await blocSetup();

  await DeviceCameras.getCameras();
}
