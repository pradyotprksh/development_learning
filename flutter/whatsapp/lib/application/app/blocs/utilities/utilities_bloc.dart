import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/device/device.dart';
import 'package:whatsapp/application/domain/domain.dart';

class UtilitiesBloc extends Bloc<UtilitiesEvent, UtilitiesState> {
  UtilitiesBloc(
    this._firebaseAuthService,
    this._firebaseFirestoreService,
    this._deviceDetails,
  ) : super(const UtilitiesState()) {
    on<InitiateConnectivityCheck>(_onConnectivityCheck);
    on<ScreenshotTaken>(_onScreenshotTaken);
    on<MessageCopyForwardEvent>(_onMessageCopiedForwarded);
  }

  final FirebaseAuthService _firebaseAuthService;
  final FirebaseFirestoreService _firebaseFirestoreService;
  final DeviceDetails _deviceDetails;

  void _onConnectivityCheck(
    InitiateConnectivityCheck event,
    Emitter<UtilitiesState> emit,
  ) async {
    await emit.forEach(
      Connectivity().onConnectivityChanged,
      onData: (connectionResult) => state.copyWith(
        connectivityResult: connectionResult,
      ),
    );
  }

  void _onScreenshotTaken(
    ScreenshotTaken event,
    Emitter<UtilitiesState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await _firebaseFirestoreService.createScreenshot(
        ScreenshotDetails(
          userId: userId,
          userDeviceDetails: await _deviceDetails.getDeviceDetails(),
          createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          route: event.name,
          arguments: event.arguments,
        ),
      );
    }
  }

  void _onMessageCopiedForwarded(
    MessageCopyForwardEvent event,
    Emitter<UtilitiesState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      var messageId = event.messageId;
      if (event.directMessageId != null) {
        messageId =
            '${CoreConstants.directMessageCollection}/${event.directMessageId}/$messageId';
      } else if (event.groupId != null) {
        messageId =
            '${CoreConstants.groupMessageCollection}/${event.groupId}/$messageId';
      }

      final messageCopyForwardDetails = MessageCopyForwardSavedDetails(
        userId: userId,
        messageId: messageId,
        isCopied: event.isCopied,
        isForwardOptionSelected: event.isForwarded,
        isSaved: event.isSaved,
        userDeviceDetails: await _deviceDetails.getDeviceDetails(),
        createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
      );

      await _firebaseFirestoreService
          .createMessageCopyForwardedSaved(messageCopyForwardDetails);
    }
  }
}
