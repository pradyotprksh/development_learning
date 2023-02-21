import 'dart:async';

abstract class NetworkListeners {
  static final StreamController<double> uploadFileSizeStream =
      StreamController();
  static final StreamController<double> downloadFileSizeStream =
      StreamController();
  static final StreamController<double> videoCallSizeStream =
      StreamController();
  static final StreamController<double> phoneCallSizeStream =
      StreamController();
}
