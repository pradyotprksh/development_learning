import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:json_widget/json_widget.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: FutureBuilder(
        future: _readHappyJsonContent(),
        builder: (_, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            return JsonWidgetBuilder(
              jsonString: snapshot.data ?? "",
              options: Options(
                  onTextChanged: (key, val) {},
                  onButtonTap: (key) {},
                  showLogs: false
              ),
            );
          }
          return const Center(
            child: CircularProgressIndicator(),
          );
        },
      ),
    );
  }

  Future<String?> _readHappyJsonContent() async {
    try {
      await Future.delayed(const Duration(seconds: 3));
      final jsonString = await rootBundle.loadString('assets/happy_json.json');
      return jsonString;
    } catch (_) {
      return null;
    }
  }
}
