import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fluttermoji/fluttermoji.dart';
import 'package:whatsapp/app/app.dart';

class AvatarView extends StatelessWidget {
  const AvatarView({super.key});

  @override
  Widget build(BuildContext context) => WillPopScope(
        onWillPop: () async {
          final value = await FluttermojiFunctions().encodeMySVGtoMap();
          if (context.mounted) {
            context.read<UserBloc>().add(SaveAvatar(value));
          }
          return Future.value(true);
        },
        child: Scaffold(
          appBar: AppBar(
            title: Text(
              context.translator.avatar,
            ),
          ),
          body: Column(
            children: [
              const Spacer(),
              FluttermojiCircleAvatar(
                radius: context.mediaQuery.size.width * 0.2,
                backgroundColor: context.themeData.primaryColor,
              ),
              const Spacer(),
              FluttermojiCustomizer(
                theme: FluttermojiThemeData(
                  labelTextStyle: context.themeData.textTheme.headlineSmall,
                  primaryBgColor: context.themeData.scaffoldBackgroundColor,
                  secondaryBgColor: context.themeData.scaffoldBackgroundColor,
                  iconColor: context.themeData.iconTheme.color,
                  selectedIconColor: context.themeData.primaryColor,
                  unselectedIconColor: context.themeData.unselectedWidgetColor,
                ),
              ),
            ],
          ),
        ),
      );
}
