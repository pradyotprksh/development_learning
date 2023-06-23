import 'package:fews/domain/domain.dart';

abstract class NewsService {
  Future<News> getNews(
    int page,
  );
}
