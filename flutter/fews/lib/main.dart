import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await _initialSetups();

  runApp(const FewsApp());
}

Future blocSetup() async {
  Bloc.observer = FewsBlocObserver();
}

Future _initialSetups() async {
  await blocSetup();
}
