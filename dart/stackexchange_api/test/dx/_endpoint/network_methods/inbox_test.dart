import 'package:stackexchange_api/core.dart';
import 'package:test/test.dart';

/// Testing [UserInbox] and [UserUnreadInbox].
void main() {
  group(
    'user inbox test cases',
    () {
      final userInboxWithOnlyRequiredParams = UserInbox(
        accessToken: 'accessToken',
      );
      final userInboxWithExtraPageParam = UserInbox(
        accessToken: 'accessToken',
        page: 1,
      );
      final userInboxWithExtraPageSizeParam = UserInbox(
        accessToken: 'accessToken',
        pageSize: 2,
      );
      final userInboxWithAllParam = UserInbox(
        accessToken: 'accessToken',
        page: 1,
        pageSize: 2,
      );

      test(
        'with only required parameters',
        () {
          expect(
            userInboxWithOnlyRequiredParams.toRequest().uri?.path,
            '/2.3/inbox',
          );
          expect(
            userInboxWithOnlyRequiredParams
                .toRequest()
                .uri
                ?.queryParameters
                .length,
            1,
          );
          expect(
            userInboxWithOnlyRequiredParams
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
        },
      );

      test(
        'with extra page parameters',
        () {
          expect(
            userInboxWithExtraPageParam.toRequest().uri?.path,
            '/2.3/inbox',
          );
          expect(
            userInboxWithExtraPageParam.toRequest().uri?.queryParameters.length,
            2,
          );
          expect(
            userInboxWithExtraPageParam
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
          expect(
            userInboxWithExtraPageParam
                .toRequest()
                .uri
                ?.queryParameters['page'],
            '1',
          );
        },
      );

      test(
        'with extra page size parameters',
        () {
          expect(
            userInboxWithExtraPageSizeParam.toRequest().uri?.path,
            '/2.3/inbox',
          );
          expect(
            userInboxWithExtraPageSizeParam
                .toRequest()
                .uri
                ?.queryParameters
                .length,
            2,
          );
          expect(
            userInboxWithExtraPageSizeParam
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
          expect(
            userInboxWithExtraPageSizeParam
                .toRequest()
                .uri
                ?.queryParameters['pagesize'],
            '2',
          );
        },
      );

      test(
        'with all parameters',
        () {
          expect(
            userInboxWithAllParam.toRequest().uri?.path,
            '/2.3/inbox',
          );
          expect(
            userInboxWithAllParam.toRequest().uri?.queryParameters.length,
            3,
          );
          expect(
            userInboxWithAllParam
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
          expect(
            userInboxWithAllParam.toRequest().uri?.queryParameters['page'],
            '1',
          );
          expect(
            userInboxWithAllParam.toRequest().uri?.queryParameters['pagesize'],
            '2',
          );
        },
      );
    },
  );

  group(
    'user unread inbox test cases',
    () {
      final userUnreadInboxWithOnlyRequiredParams = UserUnreadInbox(
        accessToken: 'accessToken',
      );
      final userUnreadInboxWithExtraPageParam = UserUnreadInbox(
        accessToken: 'accessToken',
        page: 1,
      );
      final userUnreadInboxWithExtraPageSizeParam = UserUnreadInbox(
        accessToken: 'accessToken',
        pageSize: 2,
      );
      final userUnreadInboxWithExtraSinceDateSizeParam = UserUnreadInbox(
        accessToken: 'accessToken',
        sinceDate: 123456,
      );
      final userUnreadInboxWithAllParam = UserUnreadInbox(
        accessToken: 'accessToken',
        page: 1,
        pageSize: 2,
        sinceDate: 123456,
      );

      test(
        'with only required parameters',
        () {
          expect(
            userUnreadInboxWithOnlyRequiredParams.toRequest().uri?.path,
            '/2.3/inbox/unread',
          );
          expect(
            userUnreadInboxWithOnlyRequiredParams
                .toRequest()
                .uri
                ?.queryParameters
                .length,
            1,
          );
          expect(
            userUnreadInboxWithOnlyRequiredParams
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
        },
      );

      test(
        'with extra page parameters',
        () {
          expect(
            userUnreadInboxWithExtraPageParam.toRequest().uri?.path,
            '/2.3/inbox/unread',
          );
          expect(
            userUnreadInboxWithExtraPageParam
                .toRequest()
                .uri
                ?.queryParameters
                .length,
            2,
          );
          expect(
            userUnreadInboxWithExtraPageParam
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
          expect(
            userUnreadInboxWithExtraPageParam
                .toRequest()
                .uri
                ?.queryParameters['page'],
            '1',
          );
        },
      );

      test(
        'with extra page size parameters',
        () {
          expect(
            userUnreadInboxWithExtraPageSizeParam.toRequest().uri?.path,
            '/2.3/inbox/unread',
          );
          expect(
            userUnreadInboxWithExtraPageSizeParam
                .toRequest()
                .uri
                ?.queryParameters
                .length,
            2,
          );
          expect(
            userUnreadInboxWithExtraPageSizeParam
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
          expect(
            userUnreadInboxWithExtraPageSizeParam
                .toRequest()
                .uri
                ?.queryParameters['pagesize'],
            '2',
          );
        },
      );

      test(
        'with extra since date parameters',
        () {
          expect(
            userUnreadInboxWithExtraSinceDateSizeParam.toRequest().uri?.path,
            '/2.3/inbox/unread',
          );
          expect(
            userUnreadInboxWithExtraSinceDateSizeParam
                .toRequest()
                .uri
                ?.queryParameters
                .length,
            2,
          );
          expect(
            userUnreadInboxWithExtraSinceDateSizeParam
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
          expect(
            userUnreadInboxWithExtraSinceDateSizeParam
                .toRequest()
                .uri
                ?.queryParameters['since'],
            '123456',
          );
        },
      );

      test(
        'with all parameters',
        () {
          expect(
            userUnreadInboxWithAllParam.toRequest().uri?.path,
            '/2.3/inbox/unread',
          );
          expect(
            userUnreadInboxWithAllParam.toRequest().uri?.queryParameters.length,
            4,
          );
          expect(
            userUnreadInboxWithAllParam
                .toRequest()
                .uri
                ?.queryParameters['access_token'],
            'accessToken',
          );
          expect(
            userUnreadInboxWithAllParam
                .toRequest()
                .uri
                ?.queryParameters['page'],
            '1',
          );
          expect(
            userUnreadInboxWithAllParam
                .toRequest()
                .uri
                ?.queryParameters['pagesize'],
            '2',
          );
          expect(
            userUnreadInboxWithAllParam
                .toRequest()
                .uri
                ?.queryParameters['since'],
            '123456',
          );
        },
      );
    },
  );
}
