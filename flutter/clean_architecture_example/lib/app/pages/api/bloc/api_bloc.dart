import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// A bloc class for the [ApiScreen], which will update the [ApiState]
/// based on the [ApiEvent] received.
class ApiBloc extends Bloc<ApiEvent, ApiState> {
  ApiBloc(this._apiService) : super(const ApiState()) {
    on<FetchDetails>(_fetchDetailsEvent);
  }

  final ApiService _apiService;

  /// Whenever [FetchDetails] is sent this method will be called.
  void _fetchDetailsEvent(FetchDetails event, Emitter<ApiState> emit) async {
    emit(state.copyWith(apiStatus: PageStatus.loading));
    try {
      final data = await _apiService.getApiDetails();
      emit(state.copyWith(apiStatus: PageStatus.done, apiEntity: data));
    } catch (e) {
      if (e is PetPerfectException) {
        emit(state.copyWith(
          apiStatus: PageStatus.error,
          errorMessage: e.toString(),
        ));
      } else {
        emit(state.copyWith(
          apiStatus: PageStatus.error,
        ));
      }
    }
  }
}
