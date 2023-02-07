import 'package:collection/collection.dart';
import 'package:hydrated_bloc/hydrated_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/device/device.dart';
import 'package:whatsapp/domain/domain.dart';

class NewGroupBloc extends Bloc<NewGroupEvent, NewGroupState> {
  NewGroupBloc(
    this._firebaseFirestoreService,
    this._firebaseAuthService,
    this._firebaseStorageService,
    this._deviceDetails,
  ) : super(const NewGroupState()) {
    on<FetchAccounts>(_fetchAccounts);
    on<ToggleUserSelection>(_toggleUserSelection);
    on<CreateGroupEvent>(_createGroupEvent);
  }

  final FirebaseFirestoreService _firebaseFirestoreService;
  final FirebaseAuthService _firebaseAuthService;
  final FirebaseStorageService _firebaseStorageService;
  final DeviceDetails _deviceDetails;

  void _fetchAccounts(
    FetchAccounts event,
    Emitter<NewGroupState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      await emit.forEach(
        _firebaseFirestoreService.getUserContactsAvailable(userId).stream,
        onData: (event) => state.copyWith(
          existingAccount: event,
        ),
      );
    }
  }

  void _toggleUserSelection(
    ToggleUserSelection event,
    Emitter<NewGroupState> emit,
  ) {
    final userSelected = [...state.selectedUserDetails];
    final existingAccounts = [...state.existingAccount];
    var accountDetails = existingAccounts.firstWhereOrNull(
      (element) => element.userDetails?.userId == event.userId,
    );
    if (accountDetails != null) {
      final isUserSelected = userSelected.indexWhere(
        (value) => value.userId == event.userId,
      );
      if (isUserSelected != -1) {
        userSelected.removeAt(isUserSelected);
        final index = existingAccounts.indexWhere(
          (element) => element.userDetails?.userId == event.userId,
        );
        accountDetails = accountDetails.updateSelectedValue();
        UtilsLogger.debugLog(accountDetails);
        existingAccounts[index] = accountDetails;
      } else {
        final userDetails = accountDetails.userDetails;
        if (userDetails != null) {
          userSelected.add(userDetails);
          final index = existingAccounts.indexWhere(
            (element) => element.userDetails?.userId == event.userId,
          );
          accountDetails = accountDetails.updateSelectedValue();
          UtilsLogger.debugLog(accountDetails);
          existingAccounts[index] = accountDetails;
        }
      }
    }

    emit(
      state.copyWith(
        selectedUserDetails: userSelected,
        existingAccount: existingAccounts,
      ),
    );
  }

  void _createGroupEvent(
    CreateGroupEvent event,
    Emitter<NewGroupState> emit,
  ) async {
    final userId = _firebaseAuthService.getUserId();
    if (userId != null) {
      emit(
        state.copyWith(
          pageState: PageState.loading,
        ),
      );

      String? imageUrl;
      String? firestorePath;
      final deviceDetails = await _deviceDetails.getDeviceDetails();
      try {
        firestorePath = CoreConstants.groupProfileImage().replaceAll(
          CoreConstants.userIdPlaceholder,
          userId,
        );
        imageUrl = await _firebaseStorageService.uploadFile(
          event.groupImagePath,
          firestorePath,
          {
            FirestoreItemKey.userId: userId,
            ...deviceDetails.toStringMap(),
          },
        );
      } catch (e) {
        firestorePath = null;
        FirebaseUtils.recordFlutterError(e);
      }

      await _firebaseFirestoreService.createGroupMessage(
        GroupMessageDetails(
          name: event.groupName,
          users: [
            userId,
            ...state.selectedUserDetails.map((e) => e.userId).toList(),
          ],
          createdByUserId: userId,
          createdOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          createdByUserDeviceDetails: deviceDetails,
          lastMessage: event.firstMessage,
          lastMessageOnTimeStamp: DeviceUtilsMethods.getCurrentTimeStamp(),
          lastMessageByUserId: userId,
          profileImage: imageUrl,
          firestoreFilePath: firestorePath,
        ),
      );

      emit(
        state.copyWith(
          pageState: PageState.success,
        ),
      );
    }
  }
}
