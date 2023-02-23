import 'package:emoji_picker_flutter/emoji_picker_flutter.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/application/app/utils/utils.dart';

abstract class ShowEmojiBottomSheet {
  static Future<Emoji?> showEmojiBottomSheet(
    BuildContext context,
    bool multipleSelection,
  ) async {
    var emojiTextController = TextEditingController();

    final emoji = await showModalBottomSheet<Emoji>(
      context: context,
      builder: (_) => SizedBox(
        height: context.mediaQuery.size.height * 0.5,
        child: Scaffold(
          backgroundColor: Colors.transparent,
          appBar: AppBar(
            backgroundColor: Colors.transparent,
            leading: const CloseButton(),
            title: multipleSelection
                ? TextFormField(
                    controller: emojiTextController,
                    enabled: false,
                  )
                : null,
            elevation: 0,
          ),
          body: Column(
            children: [
              Flexible(
                child: EmojiPicker(
                  onEmojiSelected: (_, emoji) {
                    if (!multipleSelection) {
                      context.navigator.pop(emoji);
                    }
                  },
                  textEditingController: emojiTextController,
                  config: Config(
                    columns: 7,
                    emojiSizeMax: 32,
                    verticalSpacing: 0,
                    horizontalSpacing: 0,
                    gridPadding: EdgeInsets.zero,
                    initCategory: Category.RECENT,
                    bgColor: context.themeData.colorScheme.background,
                    indicatorColor: context.themeData.primaryColor,
                    enableSkinTones: true,
                    showRecentsTab: true,
                    recentsLimit: 10,
                    loadingIndicator: const SizedBox.shrink(),
                    tabIndicatorAnimDuration: kTabScrollDuration,
                    categoryIcons: const CategoryIcons(),
                    buttonMode: ButtonMode.MATERIAL,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );

    return emoji;
  }
}
