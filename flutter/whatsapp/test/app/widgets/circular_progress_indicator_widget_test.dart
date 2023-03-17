import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:whatsapp/app/app.dart';

void main() {
  testWidgets(
      'constructor should create an instance of CircularProgressIndicatorWidget',
      (WidgetTester tester) async {
    final widget = CircularProgressIndicatorWidget(key: UniqueKey());

    expect(widget, isInstanceOf<CircularProgressIndicatorWidget>());
  });

  testWidgets('constructor should initialize the key property correctly',
      (WidgetTester tester) async {
    final key = UniqueKey();
    final widget = CircularProgressIndicatorWidget(key: key);

    expect(widget.key, equals(key));
  });

  testWidgets('build method should return a Center widget',
      (WidgetTester tester) async {
    await tester.pumpWidget(CircularProgressIndicatorWidget(key: UniqueKey()));

    expect(find.byType(Center), findsOneWidget);
  });

  testWidgets('build method should contain a Card widget',
      (WidgetTester tester) async {
    await tester.pumpWidget(CircularProgressIndicatorWidget(key: UniqueKey()));

    expect(find.byType(Card), findsOneWidget);
  });

  testWidgets(
      'build method should have a white background color for Card widget',
      (WidgetTester tester) async {
    await tester.pumpWidget(CircularProgressIndicatorWidget(key: UniqueKey()));

    final card = tester.widget<Card>(find.byType(Card));
    expect(card.color, equals(Colors.white));
  });

  testWidgets('build method should contain a Padding widget',
      (WidgetTester tester) async {
    await tester.pumpWidget(CircularProgressIndicatorWidget(key: UniqueKey()));

    expect(find.byType(Padding), findsWidgets);
  });

  testWidgets('build method should contain a CircularProgressIndicator widget',
      (WidgetTester tester) async {
    await tester.pumpWidget(CircularProgressIndicatorWidget(key: UniqueKey()));

    expect(find.byType(CircularProgressIndicator), findsOneWidget);
  });
}
