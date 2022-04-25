/// A data layer, which will connect to the remote and get/update it based on
/// the requirements.
///
/// This layer will not be called directly from the app layer, app layer will
/// only access the domain layer and from there domain layer will decide
/// from where to get/put the data.
export 'repositories/repositories.dart';
export 'services/services.dart';
