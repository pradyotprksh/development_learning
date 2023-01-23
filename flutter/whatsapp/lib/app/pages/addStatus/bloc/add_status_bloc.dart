import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class AddStatusBloc extends Bloc<AddStatusEvent, AddStatusState> {
  AddStatusBloc(
    String currentFont,
    this._firebaseAuthService,
    this._firebaseFirestoreService,
    this._deviceDetails,
    this._firebaseStorageService,
  ) : super(AddStatusState(currentFontFamily: currentFont)) {
    on<UpdateBackgroundColor>(_backgroundColorChangeEvent);
    on<UpdateFontFamily>(_fontChangeEvent);
    on<UploadStatus>(_uploadStatusEvent);
    on<ImageVideoSelect>(_imageVideoSelectedEvent);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;
  final FirebaseStorageService _firebaseStorageService;

  void _backgroundColorChangeEvent(
    UpdateBackgroundColor event,
    Emitter<AddStatusState> emit,
  ) {
    emit(state.copyWith(chosenColor: event.newBackgroundColor));
  }

  void _fontChangeEvent(
    UpdateFontFamily event,
    Emitter<AddStatusState> emit,
  ) {
    emit(state.copyWith(currentFontFamily: event.newFont));
  }

  void _imageVideoSelectedEvent(
    ImageVideoSelect event,
    Emitter<AddStatusState> emit,
  ) {
    emit(state.copyWith(fileDetails: event.fileDetails));
  }

  void _uploadStatusEvent(
    UploadStatus event,
    Emitter<AddStatusState> emit,
  ) async {
    final status = event.statusValue;
    final color = state.chosenColor;
    final fontFamily = state.currentFontFamily;
    final deviceDetails = await _deviceDetails.getDeviceDetails();
    String? filePathUrl;
    String? firestorePath;
    final fileDetails = state.fileDetails;

    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      emit(state.copyWith(pageState: PageState.loading));

      if (fileDetails != null) {
        String? path = fileDetails.editedFilePath;
        if (path == fileDetails.path) {
          path = fileDetails.isImage
              ? await FileCompressor.getCompressedImagePath(path)
              : await FileCompressor.getCompressedVideoPath(path);
        }
        if (path != null) {
          firestorePath = CoreConstants.userStatusImage().replaceAll(
            CoreConstants.userIdPlaceholder,
            userId,
          );
          filePathUrl = await _firebaseStorageService.uploadFile(
            path,
            firestorePath,
          );
        }
      }

      try {
        await _firebaseFirestoreService.setStatus(
          StatusDetails(
            status: status,
            color: color,
            fontFamily: fontFamily,
            userId: userId,
            userDeviceDetails: deviceDetails,
            createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
            filePathUrl: filePathUrl,
            firestoreFilePath: firestorePath,
            isFileImage: fileDetails?.isImage,
          ),
        );

        emit(state.copyWith(pageState: PageState.success));
      } catch (e) {
        FirebaseUtils.recordFlutterError(e);
        emit(state.copyWith(pageState: PageState.error));
      }
    }
  }
}
