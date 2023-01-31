import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class ExistingAccountsWidget extends StatelessWidget {
  const ExistingAccountsWidget(
    this.existingAccount, {
    super.key,
  });

  final List<UserDetails> existingAccount;

  @override
  Widget build(BuildContext context) => ListView.builder(
        primary: false,
        shrinkWrap: true,
        itemCount: existingAccount.length + 1,
        itemBuilder: (_, index) {
          if (index == 0) {
            return Padding(
              padding: ThemeEdgeInsets.all15,
              child: Text(
                '${context.translator.contactsOnWhatsApp} ${context.translator.applicationName}',
                style: context.themeData.textTheme.bodySmall,
              ),
            );
          } else {
            final userDetail = existingAccount[index - 1];

            return ListTile(
              onTap: () {
                context.navigator.pushNamed(
                  Routes.messages,
                  arguments: <String, String>{
                    Keys.userId: userDetail.userId,
                  },
                );
              },
              leading: UserImageWidget(
                profileImage: userDetail.profileImage ?? '',
                userId: userDetail.userId,
                enableAction: false,
              ),
              title: Text(
                userDetail.name ?? '',
              ),
              subtitle:
                  Text('${userDetail.phoneNumber} | ${userDetail.emailId}'),
            );
          }
        },
      );
}
