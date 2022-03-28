import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('stackexchangeApi.dx.errors');

/// A mixin on [Core] which will handle or initiate the errors request to
/// StackExchangeApi.
mixin ErrorsRequest on Core {
  /// Returns the various error codes that can be produced by the API.
  ///
  /// This method is provided for discovery, documentation, and testing
  /// purposes, it is not expected many applications will consume it during
  /// normal operation.
  ///
  /// For testing purposes, look into the /errors/{id} method which
  /// simulates errors given a code.
  ///
  /// This method returns a list of errors.
  ///
  /// Example,
  /// ```dart
  /// final errors = await stackExchange.getAllErrors();
  /// ```
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/errors](https://api.stackexchange.com/docs/errors)
  Future<Errors> getAllErrors({
    int? page,
    int? pageSize,
  }) async {
    Ensure(page != null && page < 0).isTrue('page should be > 0');
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );

    final params = AllErrors(
      page: page,
      pageSize: pageSize,
    );

    _logger.info('Getting all error list. page:$page pageSize:$pageSize');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Errors.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// This method allows you to generate an error.
  ///
  /// This method is only intended for use when testing an application or
  /// library. Unlike other methods in the API, its contract is not frozen,
  /// and new error codes may be added at any time.
  ///
  /// This method results in an error, which will be expressed with a 400
  /// HTTP status code and setting the error* properties on the wrapper
  /// object.
  ///
  /// Example,
  /// ```dart
  /// final error = await stackExchange.getError();
  /// ```
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/error](https://api.stackexchange.com/docs/simulate-error)
  Future<ErrorItem> getError({
    required int id,
  }) async {
    Ensure(id > 0).isTrue('error id should be > 0');

    final params = ErrorDetails(
      id: id,
    );

    _logger.info('Getting $id error details');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => ErrorItem.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }
}
