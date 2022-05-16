import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart' as app;
import 'package:second_hand_clothes/app/app.dart';

/// A form view screen which will show the form ui based on the form id.
class FormScreen extends StatelessWidget {
  const FormScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = context.arguments() as app.FormArguments;
    context.read<app.FormBloc>().add(app.GetDetailsFormEvent(arguments.formId));

    return BlocConsumer<app.FormBloc, app.FormState>(
      listenWhen: (previous, current) =>
          previous.formStatus != current.formStatus,
      listener: (_, formState) {
        if (formState.formStatus == FormzStatus.submissionFailure ||
            formState.formStatus == FormzStatus.invalid) {
          FormUtilsSomeMethod().handleUndoSnackBar(
            context,
            context
                    .localizationValues()
                    .mapLocalization[formState.errorMessage ?? ''] ??
                context.localizationValues().somethingWentWrong,
            () {
              context.clearSnackBars();
            },
          );
        }
      },
      buildWhen: (previous, current) =>
          previous.formStatus != current.formStatus,
      builder: (_, formState) {
        final formData = formState.formData;
        final formItems = formData.items;

        return Scaffold(
          backgroundColor: context.themeData().backgroundColor,
          extendBody: formData.extendBody ?? true,
          extendBodyBehindAppBar: formData.extendBodyBehindAppBar ?? true,
          body: formState.formStatus == FormzStatus.submissionInProgress
              ? WidgetsCircularLoadingIndicator(
                  message: context.localizationValues().fetchingFormDetails,
                )
              : formItems != null && formItems.isNotEmpty
                  ? app.WidgetsFormItems(
                      formItems: formItems,
                      itemOrientation: formData.orientation,
                    )
                  : app.WidgetsErrorRefresh(
                      onRefresh: () {
                        context.read<FormBloc>().add(
                              GetDetailsFormEvent(
                                arguments.formId,
                              ),
                            );
                      },
                    ),
        );
      },
    );
  }
}
