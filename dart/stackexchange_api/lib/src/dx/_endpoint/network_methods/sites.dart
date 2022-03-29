import 'package:stackexchange_api/core.dart';

/// A parameter class which will be used to get all the sites list from
/// StackExchange.
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
class AllSites extends Parameters {
  AllSites({
    this.page,
    this.pageSize,
  });

  int? page;

  int? pageSize;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/sites',
          queryParameters: <String, dynamic>{
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
          },
        ),
      );
}
