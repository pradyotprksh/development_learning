import 'package:fews/domain/domain.dart';

class MockErrorNewsServiceImpl extends NewsService {
  @override
  Future<News> getNews(int page, String language) => Future.delayed(
        const Duration(seconds: 0),
        () => throw Exception('Something went wrong'),
      );
}

class MockSuccessNewsServiceImpl extends NewsService {
  @override
  Future<News> getNews(int page, String language) => Future.delayed(
        const Duration(seconds: 0),
        () => News(
          meta: Meta(
            found: 60097663,
            returned: 10,
            limit: 10,
            page: page,
          ),
          data: const [
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
          ],
        ),
      );
}
