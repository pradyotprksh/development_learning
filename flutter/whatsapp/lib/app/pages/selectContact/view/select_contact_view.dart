import 'package:contacts_service/contacts_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:whatsapp/app/app.dart';

class SelectContactView extends StatelessWidget {
  const SelectContactView({super.key});

  void _checkForContactPermission(BuildContext context) async {
    final selectContactBloc = context.read<SelectContactBloc>();
    final status = await Permission.contacts.status;
    selectContactBloc.add(
      ContactPermissionStatus(status),
    );
  }

  void _askForContactPermission(BuildContext context) async {
    final selectContactBloc = context.read<SelectContactBloc>();
    final status = await Permission.contacts.request();
    selectContactBloc.add(
      ContactPermissionStatus(status),
    );
  }

  void _fetchContactDetails(BuildContext context) async {
    final selectContactBloc = context.read<SelectContactBloc>();
    if (selectContactBloc.state.permissionStatus == PermissionStatus.granted) {
      selectContactBloc.add(
        const UpdatePageStateEvent(
          pageState: PageState.loading,
        ),
      );
      var contacts = await ContactsService.getContacts();
      selectContactBloc.add(
        LocalContactsDetails(contacts),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    _checkForContactPermission(context);

    return BlocBuilder<SelectContactBloc, SelectContactState>(
      builder: (_, selectContactState) {
        if (selectContactState.checkForContacts &&
            selectContactState.permissionStatus == PermissionStatus.granted) {
          _fetchContactDetails(context);
        }

        return Scaffold(
          backgroundColor: context.themeData.backgroundColor,
          appBar: AppBar(
            title: Text(
              context.translator.selectContact,
            ),
            actions: [
              IconButton(
                onPressed: () {},
                icon: const Icon(
                  Icons.search,
                ),
              ),
              IconButton(
                onPressed: () {},
                icon: const Icon(
                  Icons.more_vert,
                ),
              ),
            ],
          ),
          body: Stack(
            alignment: Alignment.center,
            children: [
              ListView(
                children: [
                  ListTile(
                    onTap: () {},
                    contentPadding: ThemeEdgeInsets.lest15Right15,
                    leading: const Icon(
                      Icons.group,
                    ),
                    title: Text(
                      context.translator.newGroup,
                    ),
                  ),
                  ListTile(
                    onTap: () {},
                    contentPadding: ThemeEdgeInsets.lest15Right15,
                    leading: const Icon(
                      Icons.person_add,
                    ),
                    title: Text(
                      context.translator.newContact,
                    ),
                  ),
                  if (selectContactState.permissionStatus !=
                      PermissionStatus.granted)
                    GestureDetector(
                      onTap: () {
                        _askForContactPermission(context);
                      },
                      child: Padding(
                        padding: ThemeEdgeInsets.all15,
                        child: Text(
                          '${context.translator.applicationName} ${context.translator.needContactPermission}',
                          textAlign: TextAlign.center,
                        ),
                      ),
                    ),
                  if (selectContactState.permissionStatus ==
                          PermissionStatus.granted &&
                      selectContactState.existingAccount.isNotEmpty)
                    Padding(
                      padding: ThemeEdgeInsets.all15,
                      child: Text(
                        '${context.translator.contactsOnWhatsApp} ${context.translator.applicationName}',
                        style: context.themeData.textTheme.caption,
                      ),
                    ),
                  if (selectContactState.permissionStatus ==
                          PermissionStatus.granted &&
                      selectContactState.nonExistingAccount.isNotEmpty)
                    Padding(
                      padding: ThemeEdgeInsets.all15,
                      child: Text(
                        '${context.translator.inviteOnWhatsApp} ${context.translator.applicationName}',
                        style: context.themeData.textTheme.caption,
                      ),
                    ),
                ],
              ),
              if (selectContactState.pageState == PageState.loading)
                const CircularProgressIndicatorWidget(),
            ],
          ),
        );
      },
    );
  }
}