import "package:stackexchange_api/core.dart";

/// Provides a [DefaultResult] which contains the error or any message which
/// will be used to send an [StackExchangeApiException] to the application.
///
/// Mainly will be used to show error message from remote.
class DefaultResult extends Result {
  int? status;

  bool? isError;

  String? _errorMessage;
  Map<String, dynamic>? _errorDetails;
  Map<String, dynamic> otherKeys = <String, dynamic>{};
  DefaultResult();
  factory DefaultResult.fromJson(dynamic object) {
    var hasError = false;
    var errorMessage = "";
    var errorDetails = <String, dynamic>{};

    if (object is Map<String, dynamic>) {
      hasError = true;
      errorDetails = object;
      errorMessage = errorDetails["error_message"] as String;
    }

    return DefaultResult()
      ..status = object["error_id"] as int?
      ..isError = hasError
      .._errorDetails = errorDetails
      .._errorMessage = errorMessage
      ..otherKeys = collectOtherKeys(
        object,
        [
          "error_id",
          "error_message",
        ],
      );
  }

  Map<String, dynamic>? get error => _errorDetails;

  String? get message => _errorMessage;

  static Map<String, dynamic> collectOtherKeys(
    dynamic object,
    List<String> knownKeys,
  ) {
    var clone = Map<String, dynamic>.from(
      object as Map<dynamic, dynamic>,
    );

    for (var key in knownKeys) {
      clone.remove(key);
    }
    return clone;
  }
}
