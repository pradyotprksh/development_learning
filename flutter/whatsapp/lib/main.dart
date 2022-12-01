import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:path_provider/path_provider.dart' as path_provider;
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await _initialSetups();

  runApp(
    MultiBlocProvider(
      providers: [
        BlocProvider(
          create: (_) => ThemeBloc()
            ..add(
              const FetchCurrentThemeEvent(),
            ),
        ),
        BlocProvider(
          create: (_) => AuthenticationBloc()
            ..add(
              const CheckForAuthenticationStatus(),
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

  await blocSetup();
}
