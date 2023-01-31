import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class MessageView extends StatelessWidget {
  const MessageView({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = context.routeSettings?.arguments as Map<String, String>;
    context.read<MessageBloc>().add(
          FetchSelectedUserDetails(
            arguments[Keys.userId] ?? '',
          ),
        );

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
    );
  }
}
