import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class PrivacyView extends StatelessWidget {
  const PrivacyView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.privacy,
          ),
        ),
        body: ListView(
          children: [
            Padding(
              padding: ThemeEdgeInsets.all15,
              child: Text(
                context.translator.whoCanSeePersonalInfo,
                style: context.themeData.textTheme.labelSmall,
              ),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.lastSeenAndOnline),
              subtitle: Text(context.translator.everyone),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.profilePhoto),
              subtitle: Text(context.translator.everyone),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.about),
              subtitle: Text(context.translator.everyone),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.status),
              subtitle: Text(context.translator.myContacts),
            ),
            SwitchListTile(
              value: true,
              onChanged: (value) {},
              title: Text(context.translator.readReceipts),
              subtitle: Text(context.translator.readReceiptsNote),
            ),
            const Divider(),
            Padding(
              padding: ThemeEdgeInsets.all15,
              child: Text(
                context.translator.disappearingMessages,
                style: context.themeData.textTheme.labelSmall,
              ),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.defaultMessageTimer),
              subtitle: Text(context.translator.defaultMessageTimerNote),
              trailing: Text(context.translator.off),
            ),
            const Divider(),
            ListTile(
              onTap: () {},
              title: Text(context.translator.groups),
              subtitle: Text(context.translator.everyone),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.liveLocation),
              subtitle: Text(context.translator.none),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.blockedContacts),
              subtitle: Text(context.translator.none),
            ),
            ListTile(
              onTap: () {},
              title: Text(context.translator.fingerprintLock),
              subtitle: Text(context.translator.none),
            ),
          ],
        ),
      );
}
