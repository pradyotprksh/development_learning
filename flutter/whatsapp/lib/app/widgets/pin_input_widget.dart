import 'package:flutter/material.dart';
import 'package:pinput/pinput.dart';
import 'package:whatsapp/app/app.dart';

class PinInputWidget extends StatelessWidget {
  const PinInputWidget({
    super.key,
    this.validator,
    this.pinController,
  });

  final String? Function(String?)? validator;
  final TextEditingController? pinController;

  @override
  Widget build(BuildContext context) {
    final defaultPinTheme = PinTheme(
      width: context.mediaQuery.size.width / 8,
      height: 50,
      textStyle: context.themeData.textTheme.bodyLarge,
      decoration: BoxDecoration(
        border: Border.all(color: context.themeData.primaryColor),
        borderRadius: BorderRadius.circular(10),
        color: context.themeData.scaffoldBackgroundColor,
      ),
    );

    return Pinput(
      defaultPinTheme: defaultPinTheme,
      length: 8,
      obscureText: true,
      validator: validator,
      controller: pinController,
    );
  }
}
