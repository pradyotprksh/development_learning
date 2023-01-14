import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:whatsapp/app/app.dart';

class ImagePickerWidget extends StatelessWidget {
  ImagePickerWidget({
    super.key,
    required this.child,
    required this.image,
  });

  final ImagePicker _picker = ImagePicker();
  final Widget child;
  final void Function(String) image;

  @override
  Widget build(BuildContext context) => GestureDetector(
        onTap: () async {
          final source = await ImagePickerUtils.selectPickerType(context);
          if (source != null) {
            final file = await _picker.pickImage(source: source);
            if (file != null) {
              image(file.path);
            }
          }
        },
        child: child,
      );
}
