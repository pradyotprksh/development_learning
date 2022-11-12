import 'package:flutter/material.dart';
import 'package:whatsapp/core/core.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await _initialSetups();
  
  runApp(const MyApp());
}

Future<void> _initialSetups() async {
  await FirebaseUtils.initiation();
  await FirebaseUtils.appCheckInitiation();
  await FirebaseUtils.crashlyticsInitiation();
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) => MaterialApp(
      title: 'WhatsApp',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: AppPage(),
    );
}

class AppPage extends StatelessWidget {
  const AppPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) => TextButton(
      onPressed: () => throw Exception(),
      child: const Text('Throw Test Exception'),
    );
}

