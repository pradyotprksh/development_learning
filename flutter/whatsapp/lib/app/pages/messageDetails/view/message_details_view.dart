import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class MessageDetailsView extends StatelessWidget {
  const MessageDetailsView({super.key});

  @override
  Widget build(BuildContext context) {
    final details = context.routeSettings?.arguments as MessageRouteDetails;

    return Scaffold(
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(),
    );
  }
}
