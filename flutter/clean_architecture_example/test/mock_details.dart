import 'package:pet_perfect_assignemnt/domain/domain.dart';

final apiEntity = [
  ApiEntity(userId: 1, id: 1, title: 'title', body: 'body'),
  ApiEntity(userId: 1, id: 1, title: 'title', body: 'body'),
];

final displayEntity = DisplayEntity(fileSizeBytes: 1, url: 'newImageUrl');

class MockApiRepository extends ApiService {
  @override
  Future<List<ApiEntity>> getApiDetails() async => apiEntity;

  @override
  Future<DisplayEntity> getDisplayDetails() async => displayEntity;
}

class MockErrorApiRepository extends ApiService {
  @override
  Future<List<ApiEntity>> getApiDetails() async => throw NoDataException(
    message: 'No Data',
  );

  @override
  Future<DisplayEntity> getDisplayDetails() async => throw NoDataException(
    message: 'No Data',
  );
}

class MockDeviceRepository extends DeviceService {
  Map<String, String> storage = {};

  @override
  String? getUserImage() => storage['user_image'];

  @override
  void saveUserImage(String url) {
    storage['user_image'] = url;
  }
}
