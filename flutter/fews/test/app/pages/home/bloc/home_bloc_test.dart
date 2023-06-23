import 'package:bloc_test/bloc_test.dart';
import 'package:equatable/equatable.dart';
import 'package:fews/app/app.dart';
import 'package:fews/domain/domain.dart';
import 'package:flutter_test/flutter_test.dart';

import '../../../../mock/mock_news_service_impl.dart';

void main() {
  group(
    'HomeBloc test',
    () {
      setUp(
        () {
          EquatableConfig.stringify = true;
        },
      );

      blocTest(
        'emits [PageState.Success] and pageNumber count as 1 for success',
        build: () => HomeBloc(MockSuccessNewsServiceImpl()),
        act: (bloc) => bloc.add(
          const GetNews(),
        ),
        expect: () => [
          const HomeState(
            pageState: PageState.loading,
            errorMessage: null,
            newsData: [],
            pageNumber: 1,
          ),
          const HomeState(
            pageState: PageState.success,
            pageNumber: 1,
            newsData: [
              NewsData(
                uuid: 'uuid',
                title: 'title',
                description: 'description',
                keywords: 'keywords',
                snippet: 'snippet',
                url: 'url',
                imageUrl: 'imageUrl',
                language: 'language',
                source: 'source',
                categories: [],
              ),
              NewsData(
                uuid: 'uuid1',
                title: 'title1',
                description: 'description1',
                keywords: 'keywords1',
                snippet: 'snippet1',
                url: 'url1',
                imageUrl: 'imageUrl1',
                language: 'language1',
                source: 'source1',
                categories: [],
              ),
              NewsData(
                uuid: 'uuid2',
                title: 'title2',
                description: 'description2',
                keywords: 'keywords2',
                snippet: 'snippet2',
                url: 'url2',
                imageUrl: 'imageUrl2',
                language: 'language2',
                source: 'source2',
                categories: [],
              ),
            ],
          )
        ],
      );

      blocTest(
        'emits [PageState.Error]',
        build: () => HomeBloc(MockErrorNewsServiceImpl()),
        act: (bloc) => bloc.add(
          const GetNews(),
        ),
        expect: () => [
          const HomeState(
            pageState: PageState.loading,
            errorMessage: null,
            newsData: [],
            pageNumber: 1,
          ),
          const HomeState(
            pageState: PageState.error,
            pageNumber: 1,
            newsData: [],
            errorMessage: null,
          )
        ],
      );
    },
  );
}
