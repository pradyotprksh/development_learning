/// A device module for the application, anything related to device like
/// storage, getting files, etc will be handled from here.
///
/// This layer will not be called directly from the app layer, app layer will
/// only access the domain layer and from there domain layer will decide
/// from where to get/put the data.
export 'repositories/repositories.dart';
export 'services/services.dart';
export 'utils/utils.dart';