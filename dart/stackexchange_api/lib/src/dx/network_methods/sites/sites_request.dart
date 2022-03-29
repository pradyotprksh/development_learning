import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('stackexchangeApi.dx.sites');

/// A mixin on [Core] which will handle or initiate the sites request to
/// StackExchangeApi.
mixin SitesRequest on Core {
  /// Returns all sites in the network.
  ///
  /// This method allows for discovery of new sites, and changes to
  /// existing ones. Be aware that unlike normal API methods, this method
  /// should be fetched very infrequently, it is very unusual for these
  /// values to change more than once on any given day. It is suggested
  /// that you cache its return for at least one day, unless your app
  /// encounters evidence that it has changed (such as from the /info method).
  ///
  /// The [pageSize] parameter for this method is unbounded, in acknowledgement
  /// that for many applications repeatedly fetching from /sites would
  /// complicate start-up tasks needlessly.
  //
  /// This method returns a list of sites.
  ///
  /// Check [AllSites] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/errors](https://api.stackexchange.com/docs/sites)
  Future<Sites> getSites({
    int? page,
    int? pageSize,
  }) async {
    Ensure(page != null && page < 0).isTrue('page should be > 0');
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );

    final params = AllSites(
      page: page,
      pageSize: pageSize,
    );

    _logger.info('Getting all sites.');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Sites.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }
}
