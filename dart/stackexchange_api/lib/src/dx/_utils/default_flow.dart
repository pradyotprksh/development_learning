import "dart:convert";

import "package:stackexchange_api/core.dart";

/// A method which will be used to make the http request
/// from the given [params] and return the response of the type
/// [R].
///
/// [P] will always be a class which is the child of [Parameters]
/// and [R] will always be a child of [Result].
Future<R> defaultFlow<P extends Parameters, R extends Result>({
  required Core core,
  required P params,
  required BodyDeserializer<R> serializer,
}) async {
  var fiber = Fiber(
    action: () => _defaultFlow<P, R>(
      params: params,
      core: core,
      serializer: serializer,
    ),
  );

  await fiber.run();

  return fiber.future;
}

/// The private default flow which will make all the api calls and
/// help in getting the response and all
Future<R> _defaultFlow<P extends Parameters, R extends Result>({
  required P params,
  required Core core,
  required BodyDeserializer<R> serializer,
}) async {
  var request = params.toRequest();

  try {
    var handler = await core.networking.handler();
    var response = await handler.response(request);

    dynamic result = jsonDecode(response.response);
    return serializer(result);
  } on RequestFailureException catch (exception) {
    dynamic error;
    try {
      error = await jsonDecode(exception.response.response);

      if (error is Map) {
        error = DefaultResult.fromJson(error);
      }
    } catch (_) {
      error = exception.response;
    }

    throw getExceptionFromAny(error);
  }
}
