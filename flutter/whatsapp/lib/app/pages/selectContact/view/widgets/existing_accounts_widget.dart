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
                style: context.themeData.textTheme.caption,
              ),
            );
          } else {
            final userDetail = existingAccount[index - 1];

            return ListTile(
              onTap: () {},
              leading: CachedNetworkImageWidget(
                imageUrl: userDetail.profileImage ?? '',
                placeholder: CircleAvatar(
                  radius: 20,
                  backgroundColor: context.themeData.primaryColor,
                  backgroundImage: const AssetImage(
                    AssetsPath.defaultAvatar,
                  ),
                ),
                height: 40,
                width: 40,
                clipToCircle: true,
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
