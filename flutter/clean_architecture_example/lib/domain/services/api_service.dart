import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// An abstract service class for making an api call.
///
/// It will contain the calls needed to get the results.
abstract class ApiService {
  const ApiService();

  /// Get the result of api details from the
  /// https://jsonplaceholder.typicode.com/posts
  Future<List<ApiEntity>> getApiDetails();

  /// Get the result of display details from the
  /// https://random.dog/woof.json
  Future<DisplayEntity> getDisplayDetails();
}
