import 'dart:async';

abstract class NetworkListeners {
  static final StreamController<int> uploadFileSizeStream = StreamController();
  static final StreamController<int> downloadFileSizeStream =
      StreamController();
}
