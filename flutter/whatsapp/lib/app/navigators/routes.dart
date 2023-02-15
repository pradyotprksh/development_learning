import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/data/data.dart';
import 'package:whatsapp/device/device.dart';

abstract class Routes {
  static const splashRoute = '/splash';
  static const introRoute = '/intro';
  static const personaliseRoute = '/personalise';
  static const authenticateRoute = '/authenticate';
  static const homeRoute = '/home';
  static const userDetails = '/user-details';
  static const selectContact = '/select-contact';
  static const addStatusText = '/add-status-text';
  static const addStatusCamera = '/add-status-camera';
  static const settings = '/settings';
  static const messages = '/messages';
  static const groupMessages = '/group-messages';
  static const profile = '/profile';
  static const emailVerification = '/email-verification';
  static const qrCode = '/qr-code';
  static const qrCodeScanner = '/qr-code-scanner';
  static const call = '/call';
  static const newGroup = '/new-group';
  static const firebaseProfile = '/firebase-profile';
  static const pinConfirmation = '/pin-confirmation';
  static const savedMessages = '/saved-messages';

  static const initialRoute = splashRoute;

  static final routes = <String, WidgetBuilder>{
    splashRoute: (context) => const SplashView(),
    introRoute: (context) => const IntroView(),
    personaliseRoute: (context) => const PersonaliseView(),
    authenticateRoute: (context) => const AuthenticateView(),
    settings: (context) => const SettingsView(),
    qrCode: (context) => const QrCodeGeneratorView(),
    qrCodeScanner: (context) => const QrCodeScannerView(),
    pinConfirmation: (context) => const PinConfirmationView(),
    savedMessages: (context) => const SavedMessagesView(),
    firebaseProfile: (context) => ProfileScreen(
          actions: [
            SignedOutAction(
              (context) {
                context.read<AuthenticationBloc>().add(
                      const UnAuthenticateUserEvent(),
                    );
              },
            ),
            AuthStateChangeAction(
              (context, state) {},
            ),
          ],
          appBar: AppBar(),
        ),
    groupMessages: (context) => BlocProvider(
          create: (_) => GroupMessageBloc(
            FirebaseFirestoreServiceImplementation(),
            FirebaseAuthServiceImplementation(),
            DeviceDetailsImplementation(),
          ),
          child: const GroupMessageView(),
        ),
    newGroup: (context) => BlocProvider(
          create: (_) => NewGroupBloc(
            FirebaseFirestoreServiceImplementation(),
            FirebaseAuthServiceImplementation(),
            FirebaseStorageServiceImplementation(),
            DeviceDetailsImplementation(),
          )..add(const FetchAccounts()),
          child: const NewGroupView(),
        ),
    call: (context) => BlocProvider(
          create: (_) => CallBloc(
            FirebaseFirestoreServiceImplementation(),
            FirebaseAuthServiceImplementation(),
            DeviceDetailsImplementation(),
          ),
          child: const CallView(),
        ),
    profile: (context) => BlocProvider(
          create: (_) => ProfileBloc(
            FirebaseFirestoreServiceImplementation(),
            FirebaseAuthServiceImplementation(),
            FirebaseStorageServiceImplementation(),
            DeviceDetailsImplementation(),
          ),
          child: ProfileView(),
        ),
    emailVerification: (context) => EmailVerificationScreen(
          actions: [
            EmailVerifiedAction(
              () {
                context.navigator.pop();
              },
            ),
            AuthCancelledAction(
              (context) {
                context.navigator.pop();
              },
            ),
          ],
        ),
    messages: (context) => BlocProvider(
          create: (_) => DirectMessageBloc(
            FirebaseFirestoreServiceImplementation(),
            FirebaseAuthServiceImplementation(),
            DeviceDetailsImplementation(),
          ),
          child: const DirectMessageView(),
        ),
    homeRoute: (context) => MultiBlocProvider(
          providers: [
            BlocProvider(
              create: (_) => HomeBloc(
                FirebaseFirestoreServiceImplementation(),
                FirebaseAuthServiceImplementation(),
                DeviceDetailsImplementation(),
              )
                ..add(
                  const ApplicationBackgroundCheck(),
                )
                ..add(
                  const UpdateLoginHistory(),
                )
                ..add(
                  AskForPinConfirmation(
                    context
                        .read<UserBloc>()
                        .state
                        .userDetails
                        ?.lastPinConfirmationTimeStamp,
                  ),
                ),
            ),
            BlocProvider(
              create: (_) => ChatBloc(
                FirebaseFirestoreServiceImplementation(),
                FirebaseAuthServiceImplementation(),
              )
                ..add(
                  const GetDirectMessagesList(),
                )
                ..add(
                  const GetGroupMessagesList(),
                ),
            ),
            BlocProvider(
              create: (_) => StatusBloc(
                FirebaseFirestoreServiceImplementation(),
                FirebaseAuthServiceImplementation(),
                DeviceDetailsImplementation(),
              )..add(
                  const FetchStatus(),
                ),
            ),
            BlocProvider(
              create: (_) => CallsBloc(
                FirebaseFirestoreServiceImplementation(),
                FirebaseAuthServiceImplementation(),
              )..add(
                  const FetchCurrentUserCalls(),
                ),
            ),
          ],
          child: const HomeView(),
        ),
    addStatusText: (context) => BlocProvider(
          create: (_) => AddStatusBloc(
            context.read<ThemeBloc>().state.currentFontFamily,
            FirebaseAuthServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
            FirebaseStorageServiceImplementation(),
          ),
          child: AddTextStatusView(),
        ),
    addStatusCamera: (context) => BlocProvider(
          create: (_) => AddStatusBloc(
            context.read<ThemeBloc>().state.currentFontFamily,
            FirebaseAuthServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
            FirebaseStorageServiceImplementation(),
          ),
          child: AddCameraStatusView(),
        ),
    selectContact: (context) => BlocProvider(
          create: (_) => SelectContactBloc(
            FirebaseFirestoreServiceImplementation(),
            FirebaseAuthServiceImplementation(),
            DeviceDetailsImplementation(),
          ),
          child: const SelectContactView(),
        ),
    userDetails: (context) => BlocProvider(
          create: (_) => UserDetailsBloc(
            FirebaseAuthServiceImplementation(),
            FirebaseStorageServiceImplementation(),
            FirebaseFirestoreServiceImplementation(),
            DeviceDetailsImplementation(),
          )..add(
              const FetchFirebaseUserDetails(),
            ),
          child: UserDetailsView(),
        ),
  };
}
