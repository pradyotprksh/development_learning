import 'package:flex_color_scheme/flex_color_scheme.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class NetworkUsageView extends StatelessWidget {
  const NetworkUsageView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.networkUsage,
          ),
        ),
        body: BlocBuilder<NetworkBloc, NetworkState>(
          builder: (_, networkState) => ListView(
            children: [
              ListTile(
                leading: ThemeSizedBox.shrink,
                title: Text(
                  context.translator.usage,
                ),
                subtitle: Text(
                  (networkState.totalUploadSize +
                          networkState.totalDownloadSize)
                      .convertToComputerSize(),
                  style: context.themeData.textTheme.headlineLarge?.copyWith(
                    color: context.themeData.textTheme.headlineLarge?.color
                        ?.withAlpha(100),
                  ),
                ),
              ),
              ListTile(
                leading: ThemeSizedBox.shrink,
                title: Row(
                  children: [
                    Flexible(
                      child: ListTile(
                        contentPadding: ThemeEdgeInsets.zero,
                        title: Row(
                          children: [
                            Icon(
                              Icons.arrow_upward,
                              color: context.themeData.iconTheme.color,
                              size: 15,
                            ),
                            ThemeSizedBox.width5,
                            Text(context.translator.sent.capitalize),
                          ],
                        ),
                        subtitle: Text(
                          networkState.totalUploadSize.convertToComputerSize(),
                        ),
                      ),
                    ),
                    Flexible(
                      child: ListTile(
                        contentPadding: ThemeEdgeInsets.zero,
                        title: Row(
                          children: [
                            Icon(
                              Icons.arrow_downward,
                              color: context.themeData.iconTheme.color,
                              size: 15,
                            ),
                            ThemeSizedBox.width5,
                            Text(context.translator.received.capitalize),
                          ],
                        ),
                        subtitle: Text(
                          networkState.totalDownloadSize
                              .convertToComputerSize(),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
              const Divider(),
              SingleUsageWidget(
                icon: Icons.call,
                title: context.translator.calls,
                upload: networkState.totalPhoneCallSize +
                    networkState.totalVideoCallSize +
                    networkState.totalCallsDocumentWriteSize,
                download: networkState.totalPhoneCallSize +
                    networkState.totalVideoCallSize +
                    networkState.totalCallsDocumentReadSize,
                totalValue: networkState.totalUploadSize +
                    networkState.totalDownloadSize,
              ),
              SingleUsageWidget(
                icon: Icons.perm_media,
                title: context.translator.media,
                upload: networkState.totalUploadFileSize,
                download: networkState.totalDownloadFileSize,
                totalValue: networkState.totalUploadSize +
                    networkState.totalDownloadSize,
              ),
              SingleUsageWidget(
                icon: Icons.message,
                title: context.translator.messages,
                upload: networkState.totalDirectMessagesDocumentWriteSize +
                    networkState.totalGroupMessagesMessagesDocumentWriteSize +
                    networkState.totalSingleMessagesMessagesDocumentWriteSize +
                    networkState.totalSavedMessageDocumentWriteSize,
                download: networkState.totalDirectMessagesDocumentReadSize +
                    networkState.totalGroupMessagesMessagesDocumentReadSize +
                    networkState.totalSingleMessagesMessagesDocumentReadSize +
                    networkState.totalSavedMessageDocumentReadSize,
                totalValue: networkState.totalUploadSize +
                    networkState.totalDownloadSize,
              ),
              SingleUsageWidget(
                icon: Icons.person,
                title: context.translator.currentUser,
                upload: networkState.totalSavedMessageDocumentWriteSize +
                    networkState.totalContactsDocumentWriteSize,
                download: networkState.totalSavedMessageDocumentReadSize +
                    networkState.totalContactsDocumentReadSize,
                totalValue: networkState.totalUploadSize +
                    networkState.totalDownloadSize,
              ),
              SingleUsageWidget(
                icon: Icons.emoji_people,
                title: context.translator.users,
                upload: networkState.totalUserDocumentWriteSize,
                download: networkState.totalUserDocumentReadSize,
                totalValue: networkState.totalUploadSize +
                    networkState.totalDownloadSize,
              ),
              SingleUsageWidget(
                icon: Icons.pie_chart,
                title: context.translator.status,
                upload: networkState.totalStatusDocumentWriteSize,
                download: networkState.totalStatusDocumentReadSize,
                totalValue: networkState.totalUploadSize +
                    networkState.totalDownloadSize,
              ),
              SingleUsageWidget(
                icon: Icons.security,
                title: context.translator.security,
                upload: networkState.totalSecurityDocumentWriteSize,
                totalValue: networkState.totalUploadSize +
                    networkState.totalDownloadSize,
              ),
              const Divider(),
              ListTile(
                onTap: () {
                  context.read<NetworkBloc>().add(
                        const ClearNetworkUsageDetails(),
                      );
                },
                leading: ThemeSizedBox.shrink,
                title: Text(context.translator.resetStatistics),
              ),
            ],
          ),
        ),
      );
}
