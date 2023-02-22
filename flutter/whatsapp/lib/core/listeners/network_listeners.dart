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
  static final StreamController<double> userDocumentReadSizeStream =
      StreamController();
  static final StreamController<double> userDocumentWriteSizeStream =
      StreamController();
  static final StreamController<double> statusDocumentReadSizeStream =
      StreamController();
  static final StreamController<double> statusDocumentWriteSizeStream =
      StreamController();
  static final StreamController<double> securityDocumentWriteSizeStream =
      StreamController();
  static final StreamController<double> savedMessageDocumentReadSizeStream =
      StreamController();
  static final StreamController<double> savedMessageDocumentWriteSizeStream =
      StreamController();
}
