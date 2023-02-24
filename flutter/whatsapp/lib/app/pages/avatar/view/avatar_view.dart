import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fluttermoji/fluttermoji.dart';
import 'package:get/get.dart';
import 'package:whatsapp/app/app.dart';

class AvatarView extends StatelessWidget {
  const AvatarView({super.key});

  @override
  Widget build(BuildContext context) {
    // This is required. https://github.com/psk907/fluttermoji/issues/18
    Get.put(FluttermojiController());

    final theme = FluttermojiThemeData(
      labelTextStyle: context.themeData.textTheme.headlineSmall,
      primaryBgColor: context.themeData.scaffoldBackgroundColor,
      secondaryBgColor: context.themeData.scaffoldBackgroundColor,
      iconColor: context.themeData.iconTheme.color,
      selectedIconColor: context.themeData.primaryColor,
      unselectedIconColor: context.themeData.unselectedWidgetColor,
    );

    return Scaffold(
      appBar: AppBar(
        title: Text(
          context.translator.avatar,
        ),
        actions: [
          IconButton(
            onPressed: () async {
              await _saveAvatar(context);
              if (context.mounted) {
                context.navigator.pop();
              }
            },
            icon: FluttermojiSaveWidget(
              theme: theme,
              onTap: () async {
                await _saveAvatar(context);
                if (context.mounted) {
                  context.navigator.pop();
                }
              },
            ),
          ),
        ],
      ),
      body: Column(
        children: [
          const Spacer(),
          FluttermojiCircleAvatar(
            radius: 150,
            backgroundColor: context.themeData.primaryColor,
          ),
          const Spacer(),
          FluttermojiCustomizer(
            theme: theme,
            autosave: false,
          ),
        ],
      ),
    );
  }

  Future<void> _saveAvatar(BuildContext context) async {
    final value = await FluttermojiFunctions().encodeMySVGtoString();
    if (context.mounted) {
      context.read<UserBloc>().add(SaveAvatar(value));
    }
  }
}
