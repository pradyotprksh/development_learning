import 'package:emoji_picker_flutter/emoji_picker_flutter.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/core/core.dart';

class MessageFieldWithEmojiAttachmentsWidget extends StatefulWidget {
  const MessageFieldWithEmojiAttachmentsWidget({
    super.key,
    required this.onEmojiButtonPressed,
    required this.onMessageSubmitted,
    required this.closeEmojiOption,
    this.isEmojiOptionVisible = false,
  });

  final Function onEmojiButtonPressed;
  final Function closeEmojiOption;
  final Function(String) onMessageSubmitted;
  final bool isEmojiOptionVisible;

  @override
  State<MessageFieldWithEmojiAttachmentsWidget> createState() =>
      _MessageFieldWithEmojiAttachmentsWidgetState();
}

class _MessageFieldWithEmojiAttachmentsWidgetState
    extends State<MessageFieldWithEmojiAttachmentsWidget> {
  final _messageTextEditingController = TextEditingController();
  final _messageFocusNode = FocusNode();

  @override
  void initState() {
    super.initState();
    _messageFocusNode.addListener(() {
      if (_messageFocusNode.hasFocus) {
        widget.closeEmojiOption();
      }
    });
  }

  @override
  void dispose() {
    _messageFocusNode.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if (widget.isEmojiOptionVisible) _messageFocusNode.unfocus();

    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        TextFormField(
          focusNode: _messageFocusNode,
          controller: _messageTextEditingController,
          decoration: InputDecoration(
            prefixIcon: IconButton(
              onPressed: () {
                widget.onEmojiButtonPressed();
              },
              icon: const Icon(
                Icons.emoji_emotions,
              ),
            ),
            suffixIcon: IconButton(
              onPressed: () async {
                final file =
                    await MessageUtilsMethods.showAttachmentOptionsBottomSheet(
                  context,
                );
                UtilsLogger.errorLog(file?.filePath);
              },
              icon: const Icon(
                Icons.attachment,
              ),
            ),
          ),
          onFieldSubmitted: (value) {
            _messageTextEditingController.clear();
            _messageFocusNode.requestFocus();
            widget.onMessageSubmitted(value);
          },
          textInputAction: TextInputAction.send,
        ),
        if (widget.isEmojiOptionVisible)
          SizedBox(
            height: context.mediaQuery.size.height * 0.4,
            child: EmojiPicker(
              textEditingController: _messageTextEditingController,
              onBackspacePressed: () {},
              config: Config(
                columns: 7,
                emojiSizeMax: 32,
                verticalSpacing: 0,
                horizontalSpacing: 0,
                backspaceColor: context.themeData.iconTheme.color ??
                    context.themeData.primaryColor,
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
    );
  }
}
