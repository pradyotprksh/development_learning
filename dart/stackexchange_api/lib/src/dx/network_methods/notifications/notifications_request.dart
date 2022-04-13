import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('stackexchangeApi.dx.notifications');

/// A mixin on [Core] which will handle or initiate the notifications
/// request to StackExchangeApi.
mixin NotificationsRequest on Core {
  /// Returns a user's notifications.
  ///
  /// This method requires an access_token, with a scope
  /// containing "read_inbox".
  ///
  /// This method returns a list of notifications.
  ///
  /// Check [AllNotifications] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/notifications](https://api.stackexchange.com/docs/notifications)
  Future<Notifications> getAllNotifications({
    int? page,
    int? pageSize,
    required String accessToken,
  }) async {
    if (page != null) {
      Ensure(page > 0).isTrue('page should be > 0');
    }
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );

    final params = AllNotifications(
      page: page,
      pageSize: pageSize,
      accessToken: accessToken,
    );

    _logger.info('Getting all notifications. page:$page pageSize:$pageSize');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Notifications.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Returns a user's unread notifications.
  ///
  /// This method requires an access_token, with a
  /// scope containing "read_inbox".
  ///
  /// This method returns a list of notifications.
  ///
  /// Check [AllUnreadNotifications] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/notifications/unread](https://api.stackexchange.com/docs/unread-notifications)
  Future<Notifications> getAllUnreadNotifications({
    int? page,
    int? pageSize,
    required String accessToken,
  }) async {
    if (page != null) {
      Ensure(page > 0).isTrue('page should be > 0');
    }
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );

    final params = AllUnreadNotifications(
      page: page,
      pageSize: pageSize,
      accessToken: accessToken,
    );

    _logger.info('Getting all unread notifications.');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Notifications.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }
}
