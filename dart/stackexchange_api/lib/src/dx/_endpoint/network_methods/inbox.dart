import 'package:stackexchange_api/core.dart';

/// A parameter class which will be used to get the user inbox
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
class UserInbox extends Parameters {
  UserInbox({
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
          path: '/2.3/inbox',
          queryParameters: <String, String>{
            'access_token': accessToken,
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
          },
        ),
      );
}

/// A parameter class which will be used to get the user unread
/// inbox from StackExchange.
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
class UserUnreadInbox extends Parameters {
  UserUnreadInbox({
    required this.accessToken,
    this.page,
    this.pageSize,
    this.sinceDate,
  });

  int? page;

  int? pageSize;

  String accessToken;

  int? sinceDate;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/inbox/unread',
          queryParameters: <String, String>{
            'access_token': accessToken,
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
            if (sinceDate != null) 'since': sinceDate!.toString(),
          },
        ),
      );
}
