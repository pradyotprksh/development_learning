import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class HelpView extends StatelessWidget {
  const HelpView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.help,
          ),
        ),
        body: ListView(
          children: [
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.help,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.helpCenter,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.people,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.contactUs,
              ),
              subtitle: Text(
                context.translator.contactUsSubTitle,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.text_snippet,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.termsAndPrivacyPolicy,
              ),
            ),
            ListTile(
              onTap: () {},
              leading: Icon(
                Icons.info,
                color: context.themeData.iconTheme.color,
              ),
              title: Text(
                context.translator.appInfo,
              ),
            ),
          ],
        ),
      );
}
