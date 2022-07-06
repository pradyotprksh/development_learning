import 'package:dio/dio.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';

/// A client for dio which will give a dio object with the required details.
class DioClient {
  factory DioClient() => _instance;

  DioClient._privateConstructor();

  static final DioClient _instance = DioClient._privateConstructor();

  final apiDio = createApiDio();
  final displayDio = createDisplayDio();

  static Dio createApiDio() {
    var dio = Dio(BaseOptions(
      baseUrl: 'https://jsonplaceholder.typicode.com',
      receiveTimeout: 15000, // 15 seconds
      connectTimeout: 15000,
      sendTimeout: 15000,
    ));

    dio.interceptors.addAll({
      AppInterceptors(dio),
    });
    return dio;
  }

  static Dio createDisplayDio() {
    var dio = Dio(BaseOptions(
      baseUrl: 'https://random.dog',
      receiveTimeout: 15000, // 15 seconds
      connectTimeout: 15000,
      sendTimeout: 15000,
    ));

    dio.interceptors.addAll({
      AppInterceptors(dio),
    });
    return dio;
  }
}

class AppInterceptors extends Interceptor {
  AppInterceptors(this.dio);

  final Dio dio;

  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    UtilsLogger().log('${options.path} ${options.data}');
    super.onRequest(options, handler);
  }

  @override
  void onError(DioError err, ErrorInterceptorHandler handler) {
    UtilsLogger().log('${err.type} ${err.message}');
    super.onError(err, handler);
  }
}
