import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class ExistingAccountsWidget extends StatelessWidget {
  const ExistingAccountsWidget(
    this.existingAccount, {
    super.key,
  });

  final List<UserContactsAvailableDetails> existingAccount;

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
                '${context.translator.contactsOnWhatsApp}${context.translator.applicationName}',
                style: context.themeData.textTheme.bodySmall,
              ),
            );
          } else {
            final userDetail = existingAccount[index - 1].userDetails;

            if (userDetail != null) {
              return UserWithDetailsWidget(userDetail: userDetail);
            } else {
              return ThemeSizedBox.shrink;
            }
          }
        },
      );
}
