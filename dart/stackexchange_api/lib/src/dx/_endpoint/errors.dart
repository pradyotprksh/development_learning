import 'package:stackexchange_api/core.dart';

/// A parameter class which will be used to get all the error list from
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
class AllErrors extends Parameters {
  AllErrors({
    this.page,
    this.pageSize,
  });

  int? page;

  int? pageSize;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/errors',
          queryParameters: <String, int>{
            if (page != null) 'page': page!,
            if (pageSize != null) 'pageSize': pageSize!,
          },
        ),
      );
}

/// A parameter class which will be used to get the details of an
/// error list from StackExchange.
///
/// [id] is the id of the error for which the details has to be fetched.
/// And it's a required field.
class ErrorDetails extends Parameters {
  ErrorDetails({
    required this.id,
  });

  int id;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/errors/$id',
        ),
      );
}
