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
              if (formState.navigationAction?.route !=
                  context.currentRoute()) {
                Navigator.pushReplacementNamed(
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
        if ((formState.formStatus == FormzStatus.submissionFailure ||
            formState.formStatus == FormzStatus.invalid) &&
            formState.errorMessage != null) {
          app.FormUtilsSomeMethod().handleUndoSnackBar(
            context,
            context
                .localizationValues()
                .mapLocalization[formState.errorMessage] ??
                formState.errorMessage!,
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

        return WillPopScope(
          onWillPop: () async {
            if (formData.askCloseConfirmation == true) {
              return app.FormUtilsSomeMethod().askFormExitConfirmation(context);
            }
            return true;
          },
          child: Scaffold(
            key: Key(formData.id),
            backgroundColor: context.themeData().scaffoldBackgroundColor,
            extendBody: formData.extendBody ?? true,
            extendBodyBehindAppBar: formData.extendBodyBehindAppBar ?? true,
            appBar: AppBar(
              backgroundColor: Colors.transparent,
              actions: [
                if (formData.showCrossButton == true)
                  CloseButton(
                    color: context.themeData().iconTheme.color,
                  ),
              ],
            ),
            body: Stack(
              children: [
                (formItems != null && formItems.isNotEmpty)
                    ? app.WidgetsFormItems(
                  formItems: formItems,
                  itemOrientation: formData.orientation,
                )
                    : (formState.formStatus == FormzStatus.invalid ||
                    formState.formStatus ==
                        FormzStatus.submissionFailure)
                    ? app.WidgetsErrorRefresh(
                  onRefresh: () {
                    context.read<app.FormBloc>().add(
                      app.GetDetailsFormEvent(
                        arguments.formId,
                      ),
                    );
                  },
                )
                    : app.ThemesBox().shrink,
                if (formState.formStatus == FormzStatus.submissionInProgress)
                  app.WidgetsCircularLoadingIndicator(
                    message: context
                        .localizationValues()
                        .mapLocalization[formState.loadingMessage ?? ''] ??
                        context.localizationValues().fetchingFormDetails,
                  ),
              ],
            ),
          ),
        );
      },
    );
  }
}
