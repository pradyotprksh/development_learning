import 'package:dio/dio.dart';
import 'package:fews/core/core.dart';
import 'package:fews/domain/domain.dart';

class NewsServiceImplementation extends NewsService {
  final _client = RestClient(Dio());

  @override
  Future<News> getNews(
    int page,
    String language,
  ) =>
      _client.getNews(
        ConfidentialKeys.sitekey,
        language,
        page: page,
      );
}
