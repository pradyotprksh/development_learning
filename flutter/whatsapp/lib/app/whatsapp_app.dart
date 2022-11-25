import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/app/utils/extensions.dart';

class WhatsappApp extends StatelessWidget {
  const WhatsappApp({super.key});

  @override
  Widget build(BuildContext context) =>
      BlocBuilder<LocalizationsBloc, LocalizationsState>(
        builder: (_, localizationsState) => MaterialApp(
          title: context.translator.applicationName,
          localizationsDelegates:
              LocalizationsDetails.getLocalizationDelegates(),
          supportedLocales: LocalizationsDetails.getSupportedLocales(),
          locale: localizationsState.currentLocale,
        ),
      );
}
