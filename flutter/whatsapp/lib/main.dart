import 'package:flutter/material.dart';
import 'package:whatsapp/app/whatsapp_app.dart';
import 'package:whatsapp/core/core.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await _initialSetups();
  
  runApp(const WhatsappApp());
}

Future<void> _initialSetups() async {
  await FirebaseUtils.initiation();
  await FirebaseUtils.appCheckInitiation();
  await FirebaseUtils.crashlyticsInitiation();
}
