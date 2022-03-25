import 'package:http/http.dart' as http;
import 'package:stackexchange_api/core.dart';

/// A response class which will contains all the required details
/// of the request made.
class Response extends IResponse {
  Response(
    this._httpResponse,
    this._response,
  );

  final http.StreamedResponse _httpResponse;

  final String _response;

  @override
  Map<String, String> get headers => _httpResponse.headers;

  @override
  String get response => _response;

  @override
  int get statusCode => _httpResponse.statusCode;
}
