import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:second_hand_clothes/app/app.dart' as app;

/// A form view screen which will show the form ui based on the form id.
class FormScreen extends StatelessWidget {
  const FormScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final arguments = context.arguments() as app.FormArguments;
    context.read<app.FormBloc>().add(app.GetDetailsFormEvent(arguments.formId));

    return BlocConsumer<app.FormBloc, app.FormState>(
      listener: (_, formState) {
        if (formState.navigationAction != null) {
          if (formState.navigationAction?.route != null) {
            if (formState.navigationAction?.formId != null) {
              context.read<app.FormBloc>().add(
                    app.GetDetailsFormEvent(
                      formState.navigationAction?.formId ?? '',
                    ),
                  );
            } else {
              if (formState.navigationAction?.route != context.currentRoute()) {
                Navigator.pushNamed(
                  context,
                  formState.navigationAction?.route ?? '',
                );
              }
            }
          } else {
            if (formState.navigationAction?.goBack == true) {
              Navigator.pop(context);
            }
          }
        }
        if (formState.formStatus == FormzStatus.submissionFailure ||
            formState.formStatus == FormzStatus.invalid) {
          app.FormUtilsSomeMethod().handleUndoSnackBar(
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
          key: Key(formData.id),
          backgroundColor: context.themeData().backgroundColor,
          extendBody: formData.extendBody ?? true,
          extendBodyBehindAppBar: formData.extendBodyBehindAppBar ?? true,
          body: Stack(
            children: [
              if (formItems != null && formItems.isNotEmpty)
                app.WidgetsFormItems(
                  formItems: formItems,
                  itemOrientation: formData.orientation,
                ),
              if (formState.formStatus == FormzStatus.invalid ||
                  formState.formStatus == FormzStatus.submissionFailure)
                app.WidgetsErrorRefresh(
                  onRefresh: () {
                    context.read<app.FormBloc>().add(
                          app.GetDetailsFormEvent(
                            arguments.formId,
                          ),
                        );
                  },
                ),
              if (formState.formStatus == FormzStatus.submissionInProgress)
                app.WidgetsCircularLoadingIndicator(
                  message: context
                          .localizationValues()
                          .mapLocalization[formState.loadingMessage ?? ''] ??
                      context.localizationValues().fetchingFormDetails,
                ),
            ],
          ),
        );
      },
    );
  }
}
