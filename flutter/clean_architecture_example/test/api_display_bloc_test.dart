// ignore_for_file: depend_on_referenced_packages
import 'package:bloc_test/bloc_test.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:test/test.dart';

import 'mock_details.dart';

void main() {
  late MockApiRepository apiRepository;
  late MockErrorApiRepository errorApiRepository;
  late MockDeviceRepository deviceRepository;
  late ApiBloc apiBloc;
  late DisplayBloc displayBloc;

  setUp(() {
    apiRepository = MockApiRepository();
    deviceRepository = MockDeviceRepository();
    errorApiRepository = MockErrorApiRepository();
    apiBloc = ApiBloc(apiRepository);
    displayBloc = DisplayBloc(apiRepository, deviceRepository);
  });

  group(
    'ApiBloc',
    () {
      blocTest<ApiBloc, ApiState>(
        'emits ApiState with entities when '
        'FetchDetails is added',
        build: () => apiBloc,
        act: (_) => apiBloc.add(const FetchDetails()),
        expect: () => [
          const ApiState(apiStatus: PageStatus.loading),
          ApiState(apiStatus: PageStatus.done, apiEntity: apiEntity),
        ],
      );

      blocTest<ApiBloc, ApiState>(
        'emits ApiState with error '
        'FetchDetails is added',
        build: () => ApiBloc(errorApiRepository),
        act: (apiBloc) => apiBloc.add(const FetchDetails()),
        expect: () => [
          const ApiState(apiStatus: PageStatus.loading),
          const ApiState(apiStatus: PageStatus.error, errorMessage: 'No Data'),
        ],
      );
    },
  );

  group(
    'DisplayBloc',
    () {
      blocTest<DisplayBloc, DisplayState>(
        'emits DisplayState with entity when '
        'FetchDetails is added',
        build: () => displayBloc,
        act: (_) => displayBloc.add(const FetchFiles()),
        expect: () => [
          const DisplayState(displayStatus: PageStatus.loading),
          DisplayState(
            displayStatus: PageStatus.done,
            displayEntity: displayEntity,
          ),
        ],
      );

      blocTest<DisplayBloc, DisplayState>(
        'emits DisplayState with error '
        'FetchDetails is added',
        build: () => DisplayBloc(errorApiRepository, deviceRepository),
        act: (displayBloc) => displayBloc.add(const FetchFiles()),
        expect: () => [
          const DisplayState(displayStatus: PageStatus.loading),
          const DisplayState(
            displayStatus: PageStatus.error,
            errorMessage: 'No Data',
            showFloatingActionButton: true,
          ),
        ],
      );

      blocTest<DisplayBloc, DisplayState>(
        'update local storage with new user name when SaveUserImage is sent',
        build: () => displayBloc,
        act: (_) => displayBloc
          ..add(const FetchFiles())
          ..add(const SaveUserImage()),
        expect: () => [
          const DisplayState(displayStatus: PageStatus.loading),
          DisplayState(
            displayStatus: PageStatus.done,
            displayEntity: displayEntity,
          ),
        ],
        verify: (_) {
          expect(deviceRepository.getUserImage(), 'newImageUrl');
        },
      );

      blocTest<DisplayBloc, DisplayState>(
        'emits DisplayState with entity when '
        'FileLoaded is added',
        build: () => displayBloc,
        act: (_) => displayBloc.add(const FileLoaded()),
        expect: () => [
          const DisplayState(showFloatingActionButton: true),
        ],
      );
    },
  );

  tearDown(() {
    apiBloc.close();
    displayBloc.close();
  });
}
