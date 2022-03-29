import 'package:stackexchange_api/core.dart';

/// stackexchange_api Library
///
/// [credentials] : The required filed which will be helped
/// to configure initial api requests.
class StackExchangeApi extends Core
    with
        ErrorsRequest,
        SitesRequest,
        NotificationsRequest,
        InboxRequest,
        AnswersRequest {
  StackExchangeApi({
    required Credentials credentials,
  }) : super(
          credentials: credentials,
          networking: NetworkingModule(),
        );
}
