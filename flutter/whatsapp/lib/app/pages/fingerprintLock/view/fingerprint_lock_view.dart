import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class FingerprintLockView extends StatelessWidget {
  const FingerprintLockView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.fingerprintLock,
          ),
        ),
        body: BlocBuilder<AuthenticationBloc, AuthenticationState>(
          builder: (_, authState) => ListView(
            children: [
              SwitchListTile(
                value: authState.isLocalAuthEnabled,
                onChanged: (value) {
                  if (authState.isLocalAuthEnabled) {
                    context.read<AuthenticationBloc>().add(
                          const DisableBiometric(),
                        );
                  } else {
                    context.read<AuthenticationBloc>().add(
                          EnableBiometric(
                            context.translator.enableBiometric,
                          ),
                        );
                  }
                },
                title: Text(context.translator.unlockWithFingerprint),
                subtitle: Text(context.translator.unlockWithFingerprintNote),
              ),
              if (authState.isLocalAuthEnabled) const Divider(),
              if (authState.isLocalAuthEnabled)
                Padding(
                  padding: ThemeEdgeInsets.all15,
                  child: Text(
                    context.translator.automaticallyLock,
                    style: context.themeData.textTheme.labelSmall,
                  ),
                ),
              if (authState.isLocalAuthEnabled)
                RadioListTile(
                  value: LocalAuthTime.immediately,
                  groupValue: authState.localAuthTime,
                  onChanged: (value) {
                    context.read<AuthenticationBloc>().add(
                          const ChangeLocalAuthTime(
                            LocalAuthTime.immediately,
                          ),
                        );
                  },
                  title: Text(context.translator.immediately),
                ),
              if (authState.isLocalAuthEnabled)
                RadioListTile(
                  value: LocalAuthTime.after1Minute,
                  groupValue: authState.localAuthTime,
                  onChanged: (value) {
                    context.read<AuthenticationBloc>().add(
                          const ChangeLocalAuthTime(
                            LocalAuthTime.after1Minute,
                          ),
                        );
                  },
                  title: Text(context.translator.after1Minute),
                ),
              if (authState.isLocalAuthEnabled)
                RadioListTile(
                  value: LocalAuthTime.after30Minutes,
                  groupValue: authState.localAuthTime,
                  onChanged: (value) {
                    context.read<AuthenticationBloc>().add(
                          const ChangeLocalAuthTime(
                            LocalAuthTime.after30Minutes,
                          ),
                        );
                  },
                  title: Text(context.translator.after30Minutes),
                ),
              if (authState.isLocalAuthEnabled)
                SwitchListTile(
                  value: authState.showMessageOnLock,
                  onChanged: (value) {
                    context.read<AuthenticationBloc>().add(
                          const ToggleMessageVisibilityInNotification(),
                        );
                  },
                  title: Text(
                    context.translator.showContentInNotifications,
                  ),
                  subtitle: Text(
                    context.translator.showContentInNotificationsNote,
                  ),
                ),
            ],
          ),
        ),
      );
}
