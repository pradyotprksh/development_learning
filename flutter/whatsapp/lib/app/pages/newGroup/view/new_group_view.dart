import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class NewGroupView extends StatelessWidget {
  const NewGroupView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: ListTile(
            contentPadding: ThemeEdgeInsets.zero,
            title: Text(
              context.translator.newGroup,
              style: context.themeData.appBarTheme.titleTextStyle,
            ),
            subtitle: BlocBuilder<NewGroupBloc, NewGroupState>(
              builder: (_, newGroupState) => Text(
                '${newGroupState.selectedUserDetails.length}${context.translator.selected}',
                style: context.themeData.textTheme.labelSmall,
              ),
            ),
          ),
          actions: [
            IconButton(
              onPressed: () {},
              icon: const Icon(
                Icons.search,
              ),
            ),
          ],
        ),
        floatingActionButton: BlocBuilder<NewGroupBloc, NewGroupState>(
          builder: (_, newGroupState) {
            if (newGroupState.selectedUserDetails.isNotEmpty) {
              return FloatingActionButton(
                onPressed: () {
                  showModalBottomSheet<void>(
                    context: context,
                    isScrollControlled: true,
                    builder: (_) => CreateGroupWidget(
                      users: newGroupState.selectedUserDetails,
                      onSubmit: (groupName, groupImagePath) {},
                    ),
                  );
                },
                child: const Icon(
                  Icons.arrow_right_alt,
                ),
              );
            }
            return ThemeSizedBox.shrink;
          },
        ),
        body: BlocBuilder<NewGroupBloc, NewGroupState>(
          builder: (_, newGroupState) => ListView(
            children: [
              if (newGroupState.selectedUserDetails.isNotEmpty)
                ThemeSizedBox.height10,
              if (newGroupState.selectedUserDetails.isNotEmpty)
                SelectedUsersWidget(
                  selectedUserDetails: newGroupState.selectedUserDetails,
                  onDelete: (userDetails) {
                    context.read<NewGroupBloc>().add(
                          ToggleUserSelection(userDetails.userId),
                        );
                  },
                ),
              if (newGroupState.selectedUserDetails.isNotEmpty) const Divider(),
              ListView.builder(
                primary: false,
                shrinkWrap: true,
                itemCount: newGroupState.existingAccount.length,
                itemBuilder: (_, index) {
                  final accountDetails = newGroupState.existingAccount[index];
                  final userDetail = accountDetails.userDetails;

                  if (userDetail != null) {
                    return ListTile(
                      onTap: () {
                        context.read<NewGroupBloc>().add(
                              ToggleUserSelection(userDetail.userId),
                            );
                      },
                      leading: Stack(
                        alignment: Alignment.bottomRight,
                        children: [
                          UserImageWidget(
                            profileImage: userDetail.profileImage ?? '',
                            userId: userDetail.userId,
                            enableAction: false,
                          ),
                          if (accountDetails.isSelected)
                            Icon(
                              Icons.check_circle,
                              color: context.themeData.iconTheme.color,
                              size: 12,
                            ),
                        ],
                      ),
                      title: Text(
                        userDetail.name ?? '',
                      ),
                      subtitle: Text(
                          '${userDetail.phoneNumber} | ${userDetail.emailId}'),
                    );
                  } else {
                    return ThemeSizedBox.shrink;
                  }
                },
              ),
            ],
          ),
        ),
      );
}
