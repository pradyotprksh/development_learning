import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:path_provider/path_provider.dart' as path_provider;
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await _initialSetups();
  blocSetup();

  runApp(
    MultiBlocProvider(
      providers: [
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

void blocSetup() {
  WhatsAppBlocObserver();
  localStorageSetup();
}

void localStorageSetup() async {
  HydratedBloc.storage = await HydratedStorage.build(
    storageDirectory: await path_provider.getApplicationDocumentsDirectory(),
  );
}

Future<void> _initialSetups() async {
  await FirebaseUtils.initiation();
  await FirebaseUtils.appCheckInitiation();
  await FirebaseUtils.crashlyticsInitiation();
}
