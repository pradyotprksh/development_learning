import 'package:stackexchange_api/core.dart';

/// A parameter class which will be used to get all the user notifications
/// from StackExchange.
///
/// [page] and [pageSize] are the combined query. Loosely translated to get
/// n number of books (refers to [pageSize]) from mth shelf (refers to [page]).
///
/// By default [page] and [pageSize] will always be null. And the default
/// values will be taken by the server itself. Which will be always the first
/// page and max [pageSize] for it.
///
/// If [page] or [pageSize] doesn't exists then an error will be thrown by
/// server.
///
/// [accessToken] is the required field for which user the notifications has
/// to be fetched.
class AllNotifications extends Parameters {
  AllNotifications({
    required this.accessToken,
    this.page,
    this.pageSize,
  });

  int? page;

  int? pageSize;

  String accessToken;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/notifications',
          queryParameters: <String, String>{
            'access_token': accessToken,
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
          },
        ),
      );
}

/// A parameter class which will be used to get all the user unread
/// notifications from StackExchange.
///
/// [page] and [pageSize] are the combined query. Loosely translated to get
/// n number of books (refers to [pageSize]) from mth shelf (refers to [page]).
///
/// By default [page] and [pageSize] will always be null. And the default
/// values will be taken by the server itself. Which will be always the first
/// page and max [pageSize] for it.
///
/// If [page] or [pageSize] doesn't exists then an error will be thrown by
/// server.
///
/// [accessToken] is the required field for which user the notifications has
/// to be fetched.
class AllUnreadNotifications extends Parameters {
  AllUnreadNotifications({
    required this.accessToken,
    this.page,
    this.pageSize,
  });

  int? page;

  int? pageSize;

  String accessToken;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '2.3/notifications/unread',
          queryParameters: <String, String>{
            'access_token': accessToken,
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
          },
        ),
      );
}
