import 'package:pet_perfect_assignemnt/data/data.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// The repository class for [ApiService] which will implement
/// the functionality in the data layer
class ApiDataRepository extends ApiService {
  factory ApiDataRepository() => _instance;

  ApiDataRepository._privateConstructor();

  static final ApiDataRepository _instance =
      ApiDataRepository._privateConstructor();

  @override
  Future<List<ApiEntity>> getApiDetails() async {
    final response = await DioClient().apiDio.get<String>('/posts');
    final data = response.data;
    if (data != null) {
      return apiEntityListFromJson(data);
    } else {
      throw NoDataException();
    }
  }

  @override
  Future<DisplayEntity> getDisplayDetails() async {
    final response =
        await DioClient().displayDio.get<Map<String, dynamic>>('/woof.json');
    final data = response.data;
    if (data != null) {
      return DisplayEntity.fromJson(data);
    } else {
      throw NoDataException();
    }
  }
}
