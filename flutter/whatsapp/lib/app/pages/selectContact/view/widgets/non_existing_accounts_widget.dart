import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class NonExistingAccountsWidget extends StatelessWidget {
  const NonExistingAccountsWidget(
    this.nonExistingAccount, {
    super.key,
  });

  final List<ContactsNotAvailableDetails> nonExistingAccount;

  @override
  Widget build(BuildContext context) => ListView.builder(
        primary: false,
        shrinkWrap: true,
        itemCount: nonExistingAccount.length + 1,
        itemBuilder: (_, index) {
          if (index == 0) {
            return Padding(
              padding: ThemeEdgeInsets.all15,
              child: Text(
                '${context.translator.inviteOnWhatsApp}${context.translator.applicationName}',
                style: context.themeData.textTheme.bodySmall,
              ),
            );
          } else {
            final contact = nonExistingAccount[index - 1];

            final displayName = contact.displayName;
            final phoneNumber = contact.phoneNumber;
            final emailAddress = contact.emailId;

            return displayName != null ||
                    phoneNumber != null ||
                    emailAddress != null
                ? ListTile(
                    onTap: () async {
                      if (phoneNumber != null) {
                        final smsUri = Uri.parse(
                          AppUtilsMethods.smsUrl(
                            phoneNumber,
                            AppUtilsMethods.invitationMessage(
                              context.translator.applicationName,
                            ),
                          ),
                        );
                        await launchUrl(smsUri);
                      } else if (emailAddress != null) {
                        final emailAddressUri = Uri.parse(
                          AppUtilsMethods.emailUrl(
                            context.translator.applicationName,
                            emailAddress,
                          ),
                        );
                        await launchUrl(emailAddressUri);
                      }
                    },
                    leading: CircleAvatar(
                      radius: 20,
                      backgroundColor: context.themeData.primaryColor,
                      child: Text(
                        displayName?.substring(0, 1) ??
                            phoneNumber ??
                            emailAddress ??
                            '',
                        style: context.themeData.textTheme.titleLarge,
                      ),
                    ),
                    title: Text(
                      displayName ?? phoneNumber ?? emailAddress ?? '',
                    ),
                  )
                : ThemeSizedBox.shrink;
          }
        },
      );
}
