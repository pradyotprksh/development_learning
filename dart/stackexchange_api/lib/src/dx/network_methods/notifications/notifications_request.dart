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
  Future<Notifications> getAllNotifications({
    int? page,
    int? pageSize,
    required String accessToken,
  }) async {
    if (page != null) {
      Ensure(page < 0).isTrue('page should be > 0');
    }
    Ensure(accessToken.trim().isEmpty)
        .isTrue('accessToken should not be empty');
    if (pageSize != null) {
      if (pageSize < 0) {
        _logger.warning('pageSize is < 0. Will be getting empty data.');
      }
    }

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
}
