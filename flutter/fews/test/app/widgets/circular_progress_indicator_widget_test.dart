import 'package:fews/app/app.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  group(
    'CircularProgressIndicatorWidget tests',
    () {
      testWidgets(
        'Alignment test',
        (tester) async {
          await tester.pumpWidget(
            CircularProgressIndicatorWidget(
              key: UniqueKey(),
            ),
          );
          expect(find.byType(Center), findsOneWidget);
        },
      );

      testWidgets(
        'Circular indiator is present',
        (tester) async {
          await tester.pumpWidget(
            CircularProgressIndicatorWidget(
              key: UniqueKey(),
            ),
          );

          expect(find.byType(CircularProgressIndicator), findsOneWidget);
        },
      );
    },
  );
}
