import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/app/app.dart';

class ContactUsView extends StatelessWidget {
  const ContactUsView({super.key});

  @override
  Widget build(BuildContext context) => Scaffold(
        backgroundColor: context.themeData.scaffoldBackgroundColor,
        appBar: AppBar(
          title: Text(
            context.translator.contactUs,
          ),
        ),
        body: Padding(
          padding: ThemeEdgeInsets.all15,
          child: Column(
            children: [
              TextFormField(
                decoration: InputDecoration(
                  hintText: context.translator.tellUsForHelp,
                ),
              ),
              ThemeSizedBox.height30,
              CheckboxListTile(
                contentPadding: ThemeEdgeInsets.zero,
                value: true,
                onChanged: (value) {},
                title: Text.rich(
                  TextSpan(
                    text: context.translator.deviceDetailsOption,
                    children: [
                      TextSpan(
                        text: context.translator.learnMore,
                        style: context.themeData.textTheme.titleSmall?.copyWith(
                          color: context.themeData.primaryColor,
                        ),
                        recognizer: TapGestureRecognizer()..onTap = () {},
                      ),
                    ],
                  ),
                ),
              ),
              ThemeSizedBox.height30,
              Container(
                decoration: BoxDecoration(
                  border: Border.all(
                    color: context.themeData.dividerColor,
                  ),
                  borderRadius: const BorderRadius.all(
                    Radius.circular(
                      10,
                    ),
                  ),
                ),
                padding: ThemeEdgeInsets.all10,
                child: Text.rich(
                  TextSpan(
                    text: context.translator.paymentSupportOption,
                    children: [
                      TextSpan(
                        text: context.translator.helpInPayment,
                        style: context.themeData.textTheme.titleSmall?.copyWith(
                          color: context.themeData.primaryColor,
                        ),
                        recognizer: TapGestureRecognizer()..onTap = () {},
                      ),
                    ],
                  ),
                ),
              ),
              const Spacer(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Flexible(
                    child: Padding(
                      padding: ThemeEdgeInsets.all10,
                      child: Text(
                        context.translator.weWillRespond.replaceAll(
                          '%s',
                          context.translator.applicationName,
                        ),
                      ),
                    ),
                  ),
                  Flexible(
                    child: ElevatedButton(
                      onPressed: null,
                      child: Text(
                        context.translator.next,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      );
}
