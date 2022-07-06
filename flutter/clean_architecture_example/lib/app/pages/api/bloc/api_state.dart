import 'package:equatable/equatable.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// The current state of the api screen which will be used to update the
/// ui if required.
class ApiState extends Equatable {
  const ApiState({
    this.apiStatus = PageStatus.idle,
    this.apiEntity,
    this.errorMessage,
  });

  ApiState copyWith({
    PageStatus? apiStatus,
    List<ApiEntity>? apiEntity,
    String? errorMessage,
  }) =>
      ApiState(
        apiStatus: apiStatus ?? this.apiStatus,
        apiEntity: apiEntity ?? this.apiEntity,
        errorMessage: errorMessage ?? this.errorMessage,
      );

  final PageStatus apiStatus;
  final List<ApiEntity>? apiEntity;
  final String? errorMessage;

  @override
  List<Object?> get props => [apiStatus, apiEntity, errorMessage];
}
