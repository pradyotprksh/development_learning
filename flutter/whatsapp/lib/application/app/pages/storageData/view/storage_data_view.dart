import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/application/app/app.dart';

class StorageDataView extends StatelessWidget {
  const StorageDataView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.dataAndStorage,
          ),
        ),
        body: ListView(
          children: [
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.folder,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.manageStorage,
              ),
            ),
            const Divider(),
            BlocBuilder<NetworkBloc, NetworkState>(
              buildWhen: (previousState, state) =>
                  previousState.totalDownloadSize != state.totalDownloadSize ||
                  previousState.totalUploadSize != state.totalUploadSize,
              builder: (_, networkState) => ListTile(
                onTap: () {
                  context.navigator.pushNamed(Routes.networkUsage);
                },
                leading: Icon(
                  Icons.pie_chart,
                  color: context.themeData.iconTheme.color,
                ),
                title: Text(
                  context.translator.networkUsage,
                ),
                subtitle: Row(
                  children: [
                    Flexible(
                      child: Text(
                        '${networkState.totalUploadSize.convertToComputerSize()} ${context.translator.sent}',
                      ),
                    ),
                    Padding(
                      padding: ThemeEdgeInsets.all10,
                      child: Icon(
                        Icons.circle,
                        size: 5,
                        color: context.themeData.iconTheme.color,
                      ),
                    ),
                    Flexible(
                      child: Text(
                        '${networkState.totalDownloadSize.convertToComputerSize()} ${context.translator.received}',
                      ),
                    ),
                  ],
                ),
              ),
            ),
            BlocBuilder<NetworkBloc, NetworkState>(
              buildWhen: (previousState, state) =>
                  previousState.useLessDataForCalls !=
                  state.useLessDataForCalls,
              builder: (_, networkState) => ListTile(
                onTap: () {
                  context.read<NetworkBloc>().add(
                        const ToggleLessDataForCall(),
                      );
                },
                leading: ThemeSizedBox.shrink,
                trailing: GestureDetector(
                  child: Switch(
                    value: networkState.useLessDataForCalls,
                    onChanged: (value) {},
                  ),
                ),
                title: Text(context.translator.useLessDataForCalls),
              ),
            ),
            const Divider(),
            ListTile(
              title: Text(
                context.translator.mediaAutoDownload,
              ),
              subtitle: Text(
                context.translator.voiceMessageDownloadNote,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: ThemeSizedBox.shrink,
              title: Text(
                context.translator.whenUsingMobileData,
              ),
              subtitle: Text(
                context.translator.noMedia,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: ThemeSizedBox.shrink,
              title: Text(
                context.translator.whenConnectedToWifi,
              ),
              subtitle: Text(
                context.translator.noMedia,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: ThemeSizedBox.shrink,
              title: Text(
                context.translator.whenRoaming,
              ),
              subtitle: Text(
                context.translator.noMedia,
              ),
            ),
            const Divider(),
            ListTile(
              title: Text(
                context.translator.mediaUploadQuality,
              ),
              subtitle: Text(
                context.translator.chooseQuality,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: ThemeSizedBox.shrink,
              title: Text(
                context.translator.photoUploadQuality,
              ),
              subtitle: Text(
                context.translator.autoRecommended,
              ),
            ),
          ],
        ),
      );
}
