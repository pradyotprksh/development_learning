import 'package:dio/dio.dart';
import 'package:fews/core/core.dart';
import 'package:fews/domain/domain.dart';
import 'package:retrofit/retrofit.dart';

part 'rest_client.g.dart';

@RestApi(baseUrl: CoreConstants.baseUrl)
abstract class RestClient {
  factory RestClient(Dio dio, {String baseUrl}) = _RestClient;

  @GET('/all')
  Future<News> getNews(
    @Query('api_token') String apiKey,
    @Query('language') String language, {
    @Query('page') int page = 1,
  });
}
