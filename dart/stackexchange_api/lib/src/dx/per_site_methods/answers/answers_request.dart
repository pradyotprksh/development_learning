import 'package:stackexchange_api/core.dart';

final _logger = injectLogger('stackexchangeApi.dx.answers');

/// A mixin on [Core] which will handle or initiate the answers request to
/// StackExchangeApi.
mixin AnswersRequest on Core {
  /// Returns all the undeleted answers in the system.
  ///
  /// The sorts accepted by this method operate on the following fields of
  /// the answer object:
  ///
  /// activity – last_activity_date
  ///
  /// creation – creation_date
  ///
  /// votes – score
  ///
  /// activity is the default sort.
  ///
  /// It is possible to create moderately complex queries using sort, min,
  /// max, fromdate, and todate.
  ///
  /// This method returns a list of answers.
  ///
  /// Check [AllAnswers] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/answers](https://api.stackexchange.com/docs/answers)
  Future<Answers> getAllAnswers({
    int? page,
    int? pageSize,
    int? fromDate,
    int? toDate,
    Order? orderBy,
    int? minDate,
    int? maxDate,
    Sort? sortBy,
  }) async {
    Ensure(page != null && page > 0).isTrue('page should be > 0');
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );
    Checker(fromDate != null && fromDate <= 0, _logger).isTrueWarning(
      'fromDate is <= 0. Will be getting empty data.',
    );
    Checker(toDate != null && toDate <= 0, _logger).isTrueWarning(
      'toDate is <= 0. Will be getting empty data.',
    );
    Checker(minDate != null && minDate <= 0, _logger).isTrueWarning(
      'minDate is <= 0. Will be getting empty data.',
    );
    Checker(maxDate != null && maxDate <= 0, _logger).isTrueWarning(
      'maxDate is <= 0. Will be getting empty data.',
    );
    Checker(minDate != null && maxDate != null && maxDate < minDate, _logger)
        .isTrueWarning(
      'minDate > maxDate. Will be getting empty data.',
    );
    Checker(fromDate != null && toDate != null && fromDate < toDate, _logger)
        .isTrueWarning(
      'toDate > fromDate. Will be getting empty data.',
    );

    final params = AllAnswers(
      page: page,
      pageSize: pageSize,
      fromDate: fromDate,
      toDate: toDate,
      orderBy: orderBy,
      minDate: minDate,
      maxDate: maxDate,
      sortBy: sortBy,
    );

    _logger.info('Getting all answers');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Answers.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Gets the set of answers identified by ids.
  ///
  /// This is meant for batch fetching of questions. A useful trick to poll
  /// for updates is to sort by activity, with a minimum date of the last
  /// time you polled.
  ///
  /// {ids} can contain up to 100 semicolon delimited ids. To find ids
  /// programmatically look for answer_id on answer objects.
  ///
  /// The sorts accepted by this method operate on the following fields
  /// of the answer object:
  ///
  /// activity – last_activity_date
  ///
  /// creation – creation_date
  ///
  /// votes – score
  ///
  /// activity is the default sort.
  ///
  /// It is possible to create moderately complex queries using sort, min,
  /// max, fromdate, and todate.
  ///
  /// This method returns a list of answers.
  ///
  /// Check [AnswersIds] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/2.3/answers/{ids}](https://api.stackexchange.com/docs/answers-by-ids)
  Future<Answers> getAnswersByIds({
    int? page,
    int? pageSize,
    int? fromDate,
    int? toDate,
    Order? orderBy,
    int? minDate,
    int? maxDate,
    Sort? sortBy,
    required List<int> ids,
  }) async {
    Ensure(ids.isNotEmpty).isTrue('ids can\'t be empty');
    Ensure(page != null && page > 0).isTrue('page should be > 0');
    Checker(pageSize != null && pageSize <= 0, _logger).isTrueWarning(
      'pageSize is <= 0. Will be getting empty data.',
    );
    Checker(fromDate != null && fromDate <= 0, _logger).isTrueWarning(
      'fromDate is <= 0. Will be getting empty data.',
    );
    Checker(toDate != null && toDate <= 0, _logger).isTrueWarning(
      'toDate is <= 0. Will be getting empty data.',
    );
    Checker(minDate != null && minDate <= 0, _logger).isTrueWarning(
      'minDate is <= 0. Will be getting empty data.',
    );
    Checker(maxDate != null && maxDate <= 0, _logger).isTrueWarning(
      'maxDate is <= 0. Will be getting empty data.',
    );
    Checker(minDate != null && maxDate != null && maxDate < minDate, _logger)
        .isTrueWarning(
      'minDate > maxDate. Will be getting empty data.',
    );
    Checker(fromDate != null && toDate != null && fromDate < toDate, _logger)
        .isTrueWarning(
      'toDate > fromDate. Will be getting empty data.',
    );

    final params = AnswersIds(
      page: page,
      pageSize: pageSize,
      fromDate: fromDate,
      toDate: toDate,
      orderBy: orderBy,
      minDate: minDate,
      maxDate: maxDate,
      sortBy: sortBy,
      ids: ids,
    );

    _logger.info('Getting answers based on $ids');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => Answers.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Accepts an answer.
  ///
  /// Use an access_token with write_access to accept. Accept votes can only
  /// be cast on answers to questions owned by the access_token's user,
  /// and only if no other answer is accepted.
  ///
  /// To undo an accept use the /answers/{id}/accept/undo method.
  ///
  /// This method returns the accepted answer.
  ///
  /// Check [AcceptAnswer] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/2.3/answers/{id}/accept](https://api.stackexchange.com/docs/accept-answer)
  Future<AnswerItem> acceptAnswer({
    required int id,
    required String accessToken,
    bool preview = true,
  }) async {
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Ensure(credentials.key != null &&
            (credentials.key?.trim().isNotEmpty ?? false))
        .isTrue('key is not provided');

    final params = AcceptAnswer(
      id: id,
      accessToken: accessToken,
      preview: preview,
    );

    _logger.info('Accepting the answer $id');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => AnswerItem.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }
}
