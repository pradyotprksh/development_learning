import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:pet_perfect_assignemnt/app/app.dart';
import 'package:pet_perfect_assignemnt/domain/domain.dart';

/// A bloc class for the [DisplayScreen], which will update the [DisplayState]
/// based on the [DisplayEvent] received.
class DisplayBloc extends Bloc<DisplayEvent, DisplayState> {
  DisplayBloc(this._apiService, this._deviceService)
      : super(const DisplayState()) {
    on<FetchFiles>(_fetchFilesEvent);
    on<SaveUserImage>(_saveUserImageEvent);
    on<FileLoaded>(_fileLoadedEvent);
  }

  final ApiService _apiService;
  final DeviceService _deviceService;

  /// Whenever [FetchFiles] is sent this method will be called.
  void _fetchFilesEvent(FetchFiles event, Emitter<DisplayState> emit) async {
    emit(state.copyWith(displayStatus: PageStatus.loading));
    try {
      final data = await _apiService.getDisplayDetails();
      emit(state.copyWith(displayStatus: PageStatus.done, displayEntity: data));
    } catch (e) {
      if (e is PetPerfectException) {
        emit(state.copyWith(
          displayStatus: PageStatus.error,
          errorMessage: e.toString(),
          showFloatingActionButton: true,
        ));
      } else {
        emit(state.copyWith(
          displayStatus: PageStatus.error,
          showFloatingActionButton: true,
        ));
      }
    }
  }

  /// Whenever [SaveUserImage] is sent this method will be called.
  void _saveUserImageEvent(SaveUserImage event, Emitter<DisplayState> emit) {
    if (state.displayEntity?.url != null) {
      _deviceService.saveUserImage(state.displayEntity!.url);
    }
  }

  /// Whenever [FileLoaded] is sent this method will be called.
  void _fileLoadedEvent(FileLoaded event, Emitter<DisplayState> emit) {
    emit(state.copyWith(showFloatingActionButton: true));
  }
}
