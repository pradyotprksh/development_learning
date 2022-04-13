import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('stackexchangeApi.dx.notifications');

/// A mixin on [Core] which will handle or initiate the inbox
/// request to StackExchangeApi.
mixin InboxRequest on Core {
  /// Returns a user's inbox.
  ///
  /// This method requires an access_token, with a
  /// scope containing "read_inbox".
  ///
  /// This method returns a list of inbox items.
  ///
  /// Check [UserInbox] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/inbox](https://api.stackexchange.com/docs/inbox)
  Future<Inbox> getUserInbox({
    int? page,
    int? pageSize,
    required String accessToken,
  }) async {
    if (page != null) {
      Ensure(page > 0).isTrue('page should be > 0');
    }
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');

    final params = UserInbox(
      page: page,
      pageSize: pageSize,
      accessToken: accessToken,
    );

    _logger.info('Getting user inbox. page:$page pageSize:$pageSize');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Inbox.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Returns the unread items in a user's inbox.
  ///
  /// This method requires an access_token, with a
  /// scope containing "read_inbox".
  ///
  /// This method returns a list of notifications.
  ///
  /// Check [UserUnreadInbox] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/inbox/unread](https://api.stackexchange.com/docs/inbox-unread)
  Future<Inbox> getUserUnreadInbox({
    int? page,
    int? pageSize,
    int? since,
    required String accessToken,
  }) async {
    if (page != null) {
      Ensure(page > 0).isTrue('page should be > 0');
    }
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');

    final params = UserUnreadInbox(
      page: page,
      pageSize: pageSize,
      accessToken: accessToken,
      sinceDate: since,
    );

    _logger.info('Getting user unread inbox');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Inbox.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }
}
