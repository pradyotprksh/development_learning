import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class ContactUsView extends StatelessWidget {
  ContactUsView({super.key});

  final _formKey = GlobalKey<FormState>();
  final _contactUsReason = TextEditingController();

  void _submit(BuildContext context) {
    var isFormValid = _formKey.currentState?.validate() == true;
    if (isFormValid) {
      context.read<ContactUsBloc>().add(
            CreateIssue(
              _contactUsReason.text,
            ),
          );
    }
  }

  @override
  Widget build(BuildContext context) =>
      BlocListener<ContactUsBloc, ContactUsState>(
        listener: (_, contactUsState) {
          if (contactUsState.pageState == PageState.success) {
            context.navigator.pop();
          }
        },
        child: Scaffold(
          backgroundColor: context.themeData.scaffoldBackgroundColor,
          appBar: AppBar(
            title: Text(
              context.translator.contactUs,
            ),
          ),
          body: Padding(
            padding: ThemeEdgeInsets.all15,
            child: Form(
              key: _formKey,
              child: Column(
                children: [
                  TextFormField(
                    decoration: InputDecoration(
                      hintText: context.translator.tellUsForHelp,
                    ),
                    validator: (value) =>
                        AppUtilsMethods.lengthShouldBeGraterThan(
                      value,
                      10,
                      context.translator.contactUsDetails,
                    ),
                    keyboardType: TextInputType.text,
                    controller: _contactUsReason,
                  ),
                  ThemeSizedBox.height30,
                  BlocBuilder<ContactUsBloc, ContactUsState>(
                    builder: (_, contactUsState) => CheckboxListTile(
                      contentPadding: ThemeEdgeInsets.zero,
                      value: contactUsState.shareDeviceInformation,
                      onChanged: (value) {
                        context.read<ContactUsBloc>().add(
                              const ToggleDeviceInformation(),
                            );
                      },
                      title: Text.rich(
                        TextSpan(
                          text: context.translator.deviceDetailsOption,
                          children: [
                            TextSpan(
                              text: context.translator.learnMore,
                              style: context.themeData.textTheme.titleSmall
                                  ?.copyWith(
                                color: context.themeData.primaryColor,
                              ),
                              recognizer: TapGestureRecognizer()..onTap = () {},
                            ),
                          ],
                        ),
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
                            style: context.themeData.textTheme.titleSmall
                                ?.copyWith(
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
                          child: BlocBuilder<ContactUsBloc, ContactUsState>(
                        builder: (_, contactUsState) => ElevatedButton(
                          onPressed:
                              contactUsState.pageState == PageState.loading
                                  ? null
                                  : () {
                                      _submit(
                                        context,
                                      );
                                    },
                          child: Text(
                            context.translator.next,
                          ),
                        ),
                      )),
                    ],
                  ),
                ],
              ),
            ),
          ),
        ),
      );
}
