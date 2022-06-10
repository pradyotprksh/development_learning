import 'package:bang_bang/app/app.dart';
import 'package:bang_bang/bootstrap.dart';

/// The main method of the application, this is the first method executed
/// by Flutter.
void main() {
  bootstrap(
    () async {
      return const App();
    },
  );
}
