import 'package:stackexchange_api/core.dart';

/// A class which will contain the response of the [IRequestHandler].
abstract class IResponse {
  /// Headers which are provided in the request
  Map<String, String> get headers;

  /// Response from the request.
  String get response;

  /// Status code of the response
  int get statusCode;
}
