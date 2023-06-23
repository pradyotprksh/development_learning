import 'package:dio/dio.dart';
import 'package:fews/core/core.dart';
import 'package:fews/domain/domain.dart';

class NewsServiceImplementation extends NewsService {
  final _client = RestClient(Dio());

  @override
  Future<News> getNews(int page) => _client.getNews(
        ConfidentialKeys.sitekey,
        page: page,
      );
}
