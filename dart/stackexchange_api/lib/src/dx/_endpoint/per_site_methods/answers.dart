import 'package:stackexchange_api/core.dart';

/// A parameter class which will be used to get all the answers from
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
///
/// [fromDate] and [toDate] are of type int which is a unix epoch time.
///
/// [orderBy] will be used to order the response based on [Order] values.
///
/// [minDate] and [maxDate] is also a unix epoch time, which gets the responses
/// between those two dates.
///
/// [sortBy] will be used to sort the responses based on [Sort].
class AllAnswers extends Parameters {
  AllAnswers({
    this.page,
    this.pageSize,
    this.fromDate,
    this.toDate,
    this.orderBy = Order.desc,
    this.minDate,
    this.maxDate,
    this.sortBy = Sort.activity,
  });

  int? page;

  int? pageSize;

  int? fromDate;

  int? toDate;

  Order? orderBy;

  int? minDate;

  int? maxDate;

  Sort? sortBy;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/answers',
          queryParameters: <String, String>{
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
            if (fromDate != null) 'fromdate': fromDate!.toString(),
            if (toDate != null) 'todate': toDate!.toString(),
            if (orderBy != null) 'order': orderBy!.enumToString(),
            if (minDate != null) 'min': minDate!.toString(),
            if (maxDate != null) 'max': maxDate!.toString(),
            if (sortBy != null) 'sort': sortBy!.enumToString(),
            'site': 'stackoverflow',
          },
        ),
      );
}

/// A parameter class which will be used to get all the answers for the given
/// IDs from StackExchange.
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
/// [fromDate] and [toDate] are of type int which is a unix epoch time.
///
/// [orderBy] will be used to order the response based on [Order] values.
///
/// [minDate] and [maxDate] is also a unix epoch time, which gets the responses
/// between those two dates.
///
/// [sortBy] will be used to sort the responses based on [Sort].
///
/// [ids] are the required parameter, which contains all the ids of the answers
/// for which details are required.
class AnswersIds extends Parameters {
  AnswersIds({
    this.page,
    this.pageSize,
    this.fromDate,
    this.toDate,
    this.orderBy = Order.desc,
    this.minDate,
    this.maxDate,
    this.sortBy = Sort.activity,
    required this.ids,
  });

  int? page;

  int? pageSize;

  int? fromDate;

  int? toDate;

  Order? orderBy;

  int? minDate;

  int? maxDate;

  Sort? sortBy;

  List<int> ids;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/answers/${ids.semicolonJoin()}',
          queryParameters: <String, String>{
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
            if (fromDate != null) 'fromdate': fromDate!.toString(),
            if (toDate != null) 'todate': toDate!.toString(),
            if (orderBy != null) 'order': orderBy!.enumToString(),
            if (minDate != null) 'min': minDate!.toString(),
            if (maxDate != null) 'max': maxDate!.toString(),
            if (sortBy != null) 'sort': sortBy!.enumToString(),
            'site': 'stackoverflow',
          },
        ),
      );
}

/// A parameter class which will be used to accept an answer by the
/// authenticated user.
///
/// [id] of the answer which the user wants to accept.
///
/// [accessToken] is the token of the user who is going to accept the answer.
class AcceptAnswer extends Parameters {
  AcceptAnswer({
    required this.id,
    required this.accessToken,
    this.preview = true,
  });

  int id;
  String accessToken;
  bool preview;

  @override
  Request toRequest() => Request.post(
        uri: Uri(
          path: '/2.3/answers/$id/accept',
          queryParameters: <String, String>{
            'access_token': accessToken,
            'preview': preview.toString(),
          },
        ),
      );
}

/// A parameter class which will be used to undo accept an answer by the
/// authenticated user.
///
/// [id] of the answer which the user wants to accept.
///
/// [accessToken] is the token of the user who is going to accept the answer.
class UndoAcceptAnswer extends Parameters {
  UndoAcceptAnswer({
    required this.id,
    required this.accessToken,
    this.preview = true,
  });

  int id;
  String accessToken;
  bool preview;

  @override
  Request toRequest() => Request.post(
        uri: Uri(
          path: '/2.3/answers/$id/accept/undo',
          queryParameters: <String, String>{
            'access_token': accessToken,
            'preview': preview.toString(),
          },
        ),
      );
}

/// A parameter class which will be used to get all the answers comments
/// for the given IDs from StackExchange.
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
/// [fromDate] and [toDate] are of type int which is a unix epoch time.
///
/// [orderBy] will be used to order the response based on [Order] values.
///
/// [minDate] and [maxDate] is also a unix epoch time, which gets the responses
/// between those two dates.
///
/// [sortBy] will be used to sort the responses based on [Sort].
///
/// [ids] are the required parameter, which contains all the ids of the answers
/// for which details are required.
class AnswersCommentsIds extends Parameters {
  AnswersCommentsIds({
    this.page,
    this.pageSize,
    this.fromDate,
    this.toDate,
    this.orderBy = Order.desc,
    this.minDate,
    this.maxDate,
    this.sortBy = Sort.activity,
    required this.ids,
  });

  int? page;

  int? pageSize;

  int? fromDate;

  int? toDate;

  Order? orderBy;

  int? minDate;

  int? maxDate;

  Sort? sortBy;

  List<int> ids;

  @override
  Request toRequest() => Request.get(
        uri: Uri(
          path: '/2.3/answers/${ids.semicolonJoin()}/comments',
          queryParameters: <String, String>{
            if (page != null) 'page': page!.toString(),
            if (pageSize != null) 'pagesize': pageSize!.toString(),
            if (fromDate != null) 'fromdate': fromDate!.toString(),
            if (toDate != null) 'todate': toDate!.toString(),
            if (orderBy != null) 'order': orderBy!.enumToString(),
            if (minDate != null) 'min': minDate!.toString(),
            if (maxDate != null) 'max': maxDate!.toString(),
            if (sortBy != null) 'sort': sortBy!.enumToString(),
            'site': 'stackoverflow',
          },
        ),
      );
}

/// A parameter class which will be used to delete an answer by the
/// authenticated user.
///
/// [id] of the answer which the user wants to accept.
///
/// [accessToken] is the token of the user who is going to accept the answer.
class DeleteAnswer extends Parameters {
  DeleteAnswer({
    required this.id,
    required this.accessToken,
    this.preview = true,
  });

  int id;
  String accessToken;
  bool preview;

  @override
  Request toRequest() => Request.post(
        uri: Uri(
          path: '/2.3/answers/$id/delete',
          queryParameters: <String, String>{
            'access_token': accessToken,
            'preview': preview.toString(),
          },
        ),
      );
}

/// A parameter class which will be used to down vote an answer by the
/// authenticated user.
///
/// [id] of the answer which the user wants to accept.
///
/// [accessToken] is the token of the user who is going to accept the answer.
class DownVoteAnswer extends Parameters {
  DownVoteAnswer({
    required this.id,
    required this.accessToken,
    this.preview = true,
  });

  int id;
  String accessToken;
  bool preview;

  @override
  Request toRequest() => Request.post(
    uri: Uri(
      path: '/2.3/answers/$id/downvote',
      queryParameters: <String, String>{
        'access_token': accessToken,
        'preview': preview.toString(),
      },
    ),
  );
}

/// A parameter class which will be used to undo the down voted answer by the
/// authenticated user.
///
/// [id] of the answer which the user wants to accept.
///
/// [accessToken] is the token of the user who is going to accept the answer.
class UndoDownVoteAnswer extends Parameters {
  UndoDownVoteAnswer({
    required this.id,
    required this.accessToken,
    this.preview = true,
  });

  int id;
  String accessToken;
  bool preview;

  @override
  Request toRequest() => Request.post(
    uri: Uri(
      path: '/2.3/answers/$id/downvote/undo',
      queryParameters: <String, String>{
        'access_token': accessToken,
        'preview': preview.toString(),
      },
    ),
  );
}

/// A parameter class which will be used to edit an answer by the
/// authenticated user.
///
/// [id] of the answer which the user wants to accept.
///
/// [accessToken] is the token of the user who is going to accept the answer.
///
/// [body] will be the new content of the answer.
///
/// [comment] contains the description of the edit, like corrected spelling
/// and all.
class EditAnswer extends Parameters {
  EditAnswer({
    required this.id,
    required this.accessToken,
    required this.body,
    required this.comment,
    this.preview = true,
  });

  int id;
  String body;
  String comment;
  String accessToken;
  bool preview;

  @override
  Request toRequest() => Request.post(
    uri: Uri(
      path: '/2.3/answers/$id/downvote/undo',
      queryParameters: <String, String>{
        'access_token': accessToken,
        'body': body,
        'comment': comment,
        'preview': preview.toString(),
      },
    ),
  );
}

/// A parameter class which will be used to create a flag for an answer by the
/// authenticated user.
///
/// [id] of the answer which the user wants to accept.
///
/// [accessToken] is the token of the user who is going to accept the answer.
///
/// [optionId] will be the flag id.
///
/// [comment] contains the description of the edit, like corrected spelling
/// and all.
class FlagAnswer extends Parameters {
  FlagAnswer({
    required this.id,
    required this.accessToken,
    required this.optionId,
    required this.comment,
    this.preview = true,
  });

  int id;
  int optionId;
  String comment;
  String accessToken;
  bool preview;

  @override
  Request toRequest() => Request.post(
    uri: Uri(
      path: '/2.3/answers/$id/flags/add',
      queryParameters: <String, String>{
        'access_token': accessToken,
        'option_id': optionId.toString(),
        'comment': comment,
        'preview': preview.toString(),
      },
    ),
  );
}
