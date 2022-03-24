import 'package:stackexchange_api/core.dart';

/// Represents all the data necessary to make a request to the API.
abstract class Parameters {
  /// To make a [Request] and return it.
  Request toRequest();
}

/// Represents the response from an API.
abstract class Result {}
