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
    if (page != null) {
      Ensure(page > 0).isTrue('page should be > 0');
    }
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
  /// For more details go to [answers/{ids}](https://api.stackexchange.com/docs/answers-by-ids)
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
    if (page != null) {
      Ensure(page > 0).isTrue('page should be > 0');
    }
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
  /// For more details go to [answers/{id}/accept](https://api.stackexchange.com/docs/accept-answer)
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

  /// Undoes an accept on an answer.
  ///
  /// Use an access_token with write_access to undo an upvote.
  ///
  /// To accept an answer use the /answers/{id}/accept method.
  ///
  /// This method returns the un-accepted answer.
  ///
  /// Check [UndoAcceptAnswer] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [answers/{id}/accept/undo](https://api.stackexchange.com/docs/undo-accept-answer)
  Future<AnswerItem> undoAcceptAnswer({
    required int id,
    required String accessToken,
    bool preview = true,
  }) async {
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Ensure(credentials.key != null &&
            (credentials.key?.trim().isNotEmpty ?? false))
        .isTrue('key is not provided');

    final params = UndoAcceptAnswer(
      id: id,
      accessToken: accessToken,
      preview: preview,
    );

    _logger.info('Undo accepting the answer $id');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => AnswerItem.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Gets the comments on a set of answers.
  ///
  /// If you know that you have an answer id and need the comments, use
  /// this method. If you know you have a question id, use
  /// /questions/{id}/comments. If you are unsure, use /posts/{id}/comments.
  ///
  /// {ids} can contain up to 100 semicolon delimited ids. To find
  /// ids programmatically look for answer_id on answer objects.
  ///
  /// The sorts accepted by this method operate on the following fields
  /// of the comment object:
  ///
  /// creation – creation_date
  ///
  /// votes – score
  ///
  /// creation is the default sort.
  ///
  /// It is possible to create moderately complex queries using sort,
  /// min, max, fromdate, and todate.
  ///
  /// This method returns a list of comments.
  ///
  /// Check [AnswersCommentsIds] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [answers/{ids}/comments](https://api.stackexchange.com/docs/comments-on-answers)
  Future<AnswersComments> getAnswersCommentsByIds({
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
    if (page != null) {
      Ensure(page > 0).isTrue('page should be > 0');
    }
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

    final params = AnswersCommentsIds(
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

    _logger.info('Getting answers comments based on $ids');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => AnswersComments.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Deletes an answer.
  ///
  /// Note that only answer owners can delete their answers, this is not
  /// equivalent to casting a delete vote. Also be aware that there are
  /// conditions when even an answer's owner cannot delete it, and
  /// this method will respect those limits.
  ///
  /// Use an access_token with write_access to delete an answer.
  ///
  /// It is not possible to undelete an answer via the API.
  ///
  /// In practice, this method will never return an object.
  ///
  /// Check [DeleteAnswer] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [answers/{id}/delete](https://api.stackexchange.com/docs/delete-answer)
  Future<void> deleteAnswer({
    required int id,
    required String accessToken,
    bool preview = true,
  }) async {
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Ensure(credentials.key != null &&
        (credentials.key?.trim().isNotEmpty ?? false))
        .isTrue('key is not provided');

    final params = DeleteAnswer(
      id: id,
      accessToken: accessToken,
      preview: preview,
    );

    _logger.info('Delete the answer $id');

    await onlyExecuteFlow(
      core: this,
      params: params,
    );
  }

  /// Downvotes an answer.
  ///
  /// Use an access_token with write_access to upvote.
  ///
  /// To undo a downvote on an answer use the /answers/{id}/downvote/undo
  /// method.
  ///
  /// This method returns the downvoted answer.
  ///
  /// Check [DownVoteAnswer] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/answers/{id}/downvote](https://api.stackexchange.com/docs/downvote-answer)
  Future<AnswerItem> downVoteAnswer({
    required int id,
    required String accessToken,
    bool preview = true,
  }) async {
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Ensure(credentials.key != null &&
        (credentials.key?.trim().isNotEmpty ?? false))
        .isTrue('key is not provided');

    final params = DownVoteAnswer(
      id: id,
      accessToken: accessToken,
      preview: preview,
    );

    _logger.info('Down vote the answer $id');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => AnswerItem.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Undoes an downvote on an answer.
  ///
  /// Use an access_token with write_access to undo an upvote.
  ///
  /// To downvote an answer use the /answers/{id}/downvote method.
  ///
  /// This method returns the un-downvoted answer.
  ///
  /// Check [UndoDownVoteAnswer] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/answers/{id}/downvote/undo](https://api.stackexchange.com/docs/undo-downvote-answer)
  Future<AnswerItem> undoDownVoteAnswer({
    required int id,
    required String accessToken,
    bool preview = true,
  }) async {
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Ensure(credentials.key != null &&
        (credentials.key?.trim().isNotEmpty ?? false))
        .isTrue('key is not provided');

    final params = UndoDownVoteAnswer(
      id: id,
      accessToken: accessToken,
      preview: preview,
    );

    _logger.info('Undo down voted answer $id');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => AnswerItem.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Edit an existing answer.
  ///
  /// Use an access_token with write_access to edit an existing answer.
  ///
  /// Stack Exchange sites take answer quality seriously, and many
  /// checks are run on the actual websites that can require various user
  /// actions be taken. In the API, any "low quality" checks that are
  /// triggered cause the write request to fail. This includes situations
  /// where a CAPTCHA or guidance text would be displayed on the websites.
  ///
  /// This method returns the edited answer.
  ///
  /// Check [EditAnswer] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/answers/{id}/edit](https://api.stackexchange.com/docs/edit-answer)
  Future<AnswerItem> editAnswer({
    required int id,
    required String accessToken,
    required String body,
    required String comment,
    bool preview = true,
  }) async {
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Ensure(credentials.key != null &&
        (credentials.key?.trim().isNotEmpty ?? false))
        .isTrue('key is not provided');

    final params = EditAnswer(
      id: id,
      accessToken: accessToken,
      body: body,
      comment: comment,
      preview: preview,
    );

    _logger.info('Edit answer $id');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => AnswerItem.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }

  /// Casts a flag against the answer identified by id.
  ///
  /// The available flag_options on a answer are not constant,
  /// and must be fetched from /answers/{id}/flag/options.
  ///
  /// Use an access_token with write_access to create a flag.
  ///
  /// Note that the addition of is_retraction 2.3 to flag_options
  /// means that POSTing an existing Flag Id through flag_option.id
  /// when is_retraction is true will retract the flag instead of returning
  /// an error (as had happened in previous versions).
  ///
  /// This method returns the answer being flagged.
  ///
  /// Check [FlagAnswer] for parameters details.
  ///
  /// Throws [StackExchangeApiException].
  ///
  /// For more details go to [/answers/{id}/flags/add](https://api.stackexchange.com/docs/create-answer-flag)
  Future<AnswerItem> flagAnswer({
    required int id,
    required String accessToken,
    required int flagId,
    required String comment,
    bool preview = true,
  }) async {
    Ensure(accessToken.trim().isNotEmpty)
        .isTrue('accessToken should not be empty');
    Ensure(credentials.key != null &&
        (credentials.key?.trim().isNotEmpty ?? false))
        .isTrue('key is not provided');

    final params = FlagAnswer(
      id: id,
      accessToken: accessToken,
      optionId: flagId,
      comment: comment,
      preview: preview,
    );

    _logger.info('Flag answer $id');

    return defaultFlow(
      core: this,
      params: params,
      serializer: (dynamic json) => AnswerItem.fromMap(
        json as Map<String, dynamic>,
      ),
    );
  }
}
