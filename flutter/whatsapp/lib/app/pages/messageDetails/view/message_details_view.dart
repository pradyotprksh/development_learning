import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class MessageDetailsView extends StatelessWidget {
  const MessageDetailsView({super.key});

  @override
  Widget build(BuildContext context) {
    final details = context.routeSettings?.arguments as MessageRouteDetails;
    context.read<MessageDetailsBloc>().add(FetchDetails(details));

    return Scaffold(
      extendBody: true,
      extendBodyBehindAppBar: true,
      backgroundColor: context.themeData.scaffoldBackgroundColor,
      appBar: AppBar(
        actions: [
          PopupMenuButton<MessageDetailsMenuItem>(
            onSelected: (item) {
              switch (item) {
                case MessageDetailsMenuItem.share:
                  break;
                case MessageDetailsMenuItem.edit:
                  break;
                case MessageDetailsMenuItem.viewInAddressBook:
                  break;
                case MessageDetailsMenuItem.viewSecurityCode:
                  break;
                case MessageDetailsMenuItem.changeSubject:
                  break;
              }
            },
            color: context.themeData.popupMenuTheme.color,
            itemBuilder: (_) => [
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.edit,
                  child: Text(
                    context.translator.edit,
                  ),
                ),
              if (details.isGroup)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.changeSubject,
                  child: Text(
                    context.translator.changeSubject,
                  ),
                ),
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.share,
                  child: Text(
                    context.translator.share,
                  ),
                ),
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.viewInAddressBook,
                  child: Text(
                    context.translator.viewInAddressBook,
                  ),
                ),
              if (details.isDirectMessage)
                PopupMenuItem(
                  value: MessageDetailsMenuItem.viewSecurityCode,
                  child: Text(
                    context.translator.viewSecurityCode,
                  ),
                ),
            ],
            icon: const Icon(
              Icons.more_vert,
            ),
          ),
        ],
      ),
      body: BlocBuilder<MessageDetailsBloc, MessageDetailsState>(
        builder: (_, messageState) {
          final groupDetails =
              messageState.groupMessageDetails?.groupMessageDetails;

          final imageAttachments = messageState.media
              .where(
                (element) =>
                    element.attachments != null &&
                    element.attachments?.isNotEmpty == true,
              )
              .map((e) => e.attachments)
              .expand<FileInformationDetails>(
                (element) => element as List<FileInformationDetails>,
              )
              .toList();

          return ListView(
            children: [
              ThemeSizedBox.height20,
              if (groupDetails != null)
                GroupImageWidget(
                  profileImage: groupDetails.profileImage ?? '',
                  groupId: groupDetails.groupId,
                  enableAction: false,
                  size: 180,
                ),
              ThemeSizedBox.height20,
              if (groupDetails != null)
                Text(
                  groupDetails.name,
                  textAlign: TextAlign.center,
                  style: context.themeData.textTheme.headlineMedium,
                ),
              ThemeSizedBox.height20,
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  IconButton(
                    onPressed: () {
                      _makeVideoOrPhoneCall(context, true);
                    },
                    icon: Icon(
                      Icons.call,
                      color: context.themeData.primaryColor,
                      size: 30,
                    ),
                  ),
                  ThemeSizedBox.width10,
                  IconButton(
                    onPressed: () {
                      _makeVideoOrPhoneCall(context, false);
                    },
                    icon: Icon(
                      Icons.videocam,
                      color: context.themeData.primaryColor,
                      size: 30,
                    ),
                  ),
                  ThemeSizedBox.width10,
                  IconButton(
                    onPressed: () {},
                    icon: Icon(
                      Icons.search,
                      color: context.themeData.primaryColor,
                      size: 30,
                    ),
                  ),
                ],
              ),
              const AppBarDividerWidget(
                thickness: 10,
              ),
              if (groupDetails != null)
                ListTile(
                  onTap: () {},
                  title: Text(
                    context.translator.addGroupDescription,
                    style: context.themeData.textTheme.labelLarge?.copyWith(
                      color: context.themeData.primaryColor,
                    ),
                  ),
                  subtitle: Text(
                    '${context.translator.created}${AppUtilsMethods.timeAgo(
                      groupDetails.createdOnTimeStamp,
                      context,
                    )}',
                  ),
                ),
              if (groupDetails != null)
                const AppBarDividerWidget(
                  thickness: 10,
                ),
              if (messageState.media.isNotEmpty)
                Padding(
                  padding: ThemeEdgeInsets.all15,
                  child: GestureDetector(
                    onTap: () {},
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          context.translator.mediaLinksDocs,
                          style: context.themeData.textTheme.labelSmall,
                        ),
                        Row(
                          children: [
                            Text(
                              '${messageState.attachmentsLength}',
                              style: context.themeData.textTheme.labelSmall,
                            ),
                            const Icon(
                              Icons.keyboard_arrow_right,
                              size: 12,
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              if (imageAttachments.isNotEmpty)
                SizedBox(
                  height: 150,
                  child: ListView.builder(
                    physics: const ClampingScrollPhysics(),
                    shrinkWrap: true,
                    padding: ThemeEdgeInsets.left15TopBottom5,
                    scrollDirection: Axis.horizontal,
                    itemCount: imageAttachments.length,
                    itemBuilder: (_, index) => SizedBox(
                      height: 150,
                      width: 100,
                      child: Padding(
                        padding: ThemeEdgeInsets.right15,
                        child: CachedAttachmentPreviewWidget(
                          fileDetails: imageAttachments[index],
                          showOnlyImage: true,
                        ),
                      ),
                    ),
                  ),
                ),
              const AppBarDividerWidget(
                thickness: 10,
              ),
              ListTile(
                onTap: () {},
                leading: Icon(
                  Icons.notifications,
                  color: context.themeData.iconTheme.color,
                ),
                trailing: GestureDetector(
                  child: Switch(
                    value: false,
                    onChanged: (value) {},
                  ),
                ),
                title: Text(context.translator.muteNotifications),
              ),
              ListTile(
                onTap: () {},
                leading: Icon(
                  Icons.music_note,
                  color: context.themeData.iconTheme.color,
                ),
                title: Text(context.translator.customNotifications),
              ),
              ListTile(
                onTap: () {},
                leading: Icon(
                  Icons.image,
                  color: context.themeData.iconTheme.color,
                ),
                title: Text(context.translator.mediaVisibility),
              ),
              const AppBarDividerWidget(
                thickness: 10,
              ),
              ListTile(
                onTap: () {},
                leading: Icon(
                  Icons.lock,
                  color: context.themeData.iconTheme.color,
                ),
                title: Text(context.translator.encryption),
                subtitle: Text(context.translator.encryptionNote),
              ),
              ListTile(
                onTap: () {},
                leading: Icon(
                  Icons.timer,
                  color: context.themeData.iconTheme.color,
                ),
                title: Text(context.translator.disappearingMessages),
                subtitle: Text(context.translator.off),
              ),
              const AppBarDividerWidget(
                thickness: 10,
              ),
              if (groupDetails != null && messageState.usersDetails.isNotEmpty)
                Padding(
                  padding: ThemeEdgeInsets.all15,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        '${messageState.usersDetails.length}${context.translator.participants}',
                        style: context.themeData.textTheme.labelSmall,
                      ),
                      InkWell(
                        onTap: () {},
                        child: const Icon(
                          Icons.search,
                        ),
                      ),
                    ],
                  ),
                ),
              if (groupDetails != null && messageState.usersDetails.isNotEmpty)
                ListView.builder(
                  primary: false,
                  shrinkWrap: true,
                  padding: ThemeEdgeInsets.zero,
                  itemCount: messageState.usersDetails.length,
                  itemBuilder: (_, index) => UserWithDetailsWidget(
                    userDetail: messageState.usersDetails[index],
                    onTap: (userDetails) {},
                  ),
                ),
              if (groupDetails != null && messageState.usersDetails.isNotEmpty)
                const AppBarDividerWidget(
                  thickness: 10,
                ),
            ],
          );
        },
      ),
    );
  }

  void _makeVideoOrPhoneCall(
    BuildContext context,
    bool isPhoneCall,
  ) {
    final users = context.read<MessageDetailsBloc>().state.usersDetails;

    if (users.isNotEmpty) {
      context.navigator.pushNamed(
        Routes.call,
        arguments: CallDetailsArguments(
          userDetails: context.read<MessageDetailsBloc>().state.usersDetails,
          isPhoneCall: isPhoneCall,
          isVideoCall: !isPhoneCall,
          isGroupCall: false,
        ),
      );
    }
  }
}
