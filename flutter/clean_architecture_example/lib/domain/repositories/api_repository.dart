import 'package:pet_perfect_assignemnt/data/data.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// The domain layer repository for the api, this will fetch the data
/// from the other layer.
class DomainApiRepository implements ApiService {
  factory DomainApiRepository() => _instance;

  DomainApiRepository._privateConstructor();

  static final DomainApiRepository _instance =
      DomainApiRepository._privateConstructor();

  final _dataRepository = ApiDataRepository();

  @override
  Future<List<ApiEntity>> getApiDetails() => _dataRepository.getApiDetails();

  @override
  Future<DisplayEntity> getDisplayDetails() =>
      _dataRepository.getDisplayDetails();
}
