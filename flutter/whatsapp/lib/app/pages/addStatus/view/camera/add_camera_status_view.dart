import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:whatsapp/app/app.dart';

class AddCameraStatusView extends StatelessWidget {
  AddCameraStatusView({super.key});

  void _submitStatus(BuildContext context) {
    if (_formKey.currentState?.validate() == true) {
      context.read<AddStatusBloc>().add(
            UploadStatus(_statusController.text),
          );
    }
  }

  final _formKey = GlobalKey<FormState>();
  final _statusController = TextEditingController();

  @override
  Widget build(BuildContext context) =>
      BlocConsumer<AddStatusBloc, AddStatusState>(
        listener: (_, addStatusState) {
          if (addStatusState.pageState == PageState.success) {
            context.navigator.pop();
          }
        },
        builder: (_, addStatusState) {
          final fileDetails = addStatusState.fileDetails;
          final filePath = fileDetails?.path;
          final croppedImagePath = fileDetails?.editedFilePath;
          final isPicture = fileDetails?.isImage == true;

          return Scaffold(
            backgroundColor: context.themeData.scaffoldBackgroundColor,
            appBar: AppBar(
              leading: const CloseButton(),
              elevation: filePath == null
                  ? 0
                  : context.themeData.appBarTheme.elevation,
              backgroundColor: filePath == null
                  ? Colors.transparent
                  : context.themeData.appBarTheme.backgroundColor,
              actions: filePath != null
                  ? [
                      if (croppedImagePath != filePath)
                        IconButton(
                          onPressed: () {
                            final statusBloc = context.read<AddStatusBloc>();
                            final currentFiledDetails =
                                statusBloc.state.fileDetails;
                            if (currentFiledDetails != null) {
                              statusBloc.add(
                                ImageVideoSelect(
                                  currentFiledDetails.copyWith(
                                    filePath,
                                  ),
                                ),
                              );
                            }
                          },
                          icon: const Icon(
                            Icons.undo,
                          ),
                        ),
                      if (isPicture)
                        IconButton(
                          onPressed: () async {
                            final statusBloc = context.read<AddStatusBloc>();
                            final currentFiledDetails =
                                statusBloc.state.fileDetails;
                            final croppedPath =
                                await ImagePickerUtils.getCroppedImage(
                              filePath,
                              null,
                              null,
                              [
                                AndroidUiSettings(
                                  toolbarTitle: AppConstants.applicationName,
                                  toolbarColor: context
                                      .themeData.appBarTheme.backgroundColor,
                                  toolbarWidgetColor:
                                      context.themeData.iconTheme.color,
                                  initAspectRatio:
                                      CropAspectRatioPreset.original,
                                  lockAspectRatio: false,
                                ),
                                IOSUiSettings(
                                  title: AppConstants.applicationName,
                                ),
                                WebUiSettings(
                                  context: context,
                                ),
                              ],
                            );
                            if (croppedPath != null &&
                                currentFiledDetails != null) {
                              statusBloc.add(
                                ImageVideoSelect(
                                  currentFiledDetails.copyWith(
                                    croppedPath,
                                  ),
                                ),
                              );
                            }
                          },
                          icon: const Icon(
                            Icons.crop_rotate,
                          ),
                        ),
                      if (isPicture)
                        IconButton(
                          onPressed: () {},
                          icon: const Icon(
                            Icons.emoji_emotions,
                          ),
                        ),
                      if (isPicture)
                        IconButton(
                          onPressed: () {},
                          icon: const Icon(
                            Icons.text_fields,
                          ),
                        ),
                      if (isPicture)
                        IconButton(
                          onPressed: () {},
                          icon: const Icon(
                            Icons.edit,
                          ),
                        ),
                    ]
                  : null,
            ),
            extendBodyBehindAppBar: true,
            extendBody: true,
            body: croppedImagePath == null
                ? CameraOptionWidget(
                    onMediaSelected: (path) {
                      context.read<AddStatusBloc>().add(
                            ImageVideoSelect(path),
                          );
                    },
                  )
                : Stack(
                    alignment: Alignment.center,
                    children: [
                      SizedBox(
                        height: double.infinity,
                        width: double.infinity,
                        child: isPicture
                            ? Image.file(
                                File(
                                  croppedImagePath,
                                ),
                                fit: BoxFit.contain,
                              )
                            : filePath != null
                                ? VideoWidget(path: filePath)
                                : ThemeSizedBox.shrink,
                      ),
                      Align(
                        alignment: Alignment.bottomCenter,
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            Padding(
                              padding: ThemeEdgeInsets.left15Right15,
                              child: Form(
                                key: _formKey,
                                child: TextFormField(
                                  controller: _statusController,
                                  decoration: InputDecoration(
                                    fillColor: context.themeData.primaryColor,
                                    filled: true,
                                    border: UnderlineInputBorder(
                                      borderRadius: BorderRadius.circular(20),
                                    ),
                                    enabledBorder: UnderlineInputBorder(
                                      borderRadius: BorderRadius.circular(20),
                                    ),
                                    hintText:
                                        context.translator.addCaptionForStatus,
                                  ),
                                  cursorColor:
                                      context.themeData.colorScheme.background,
                                ),
                              ),
                            ),
                            ThemeSizedBox.height10,
                            UploadStatusWidget(
                              onPressed: () {
                                _submitStatus(context);
                              },
                            ),
                          ],
                        ),
                      ),
                      if (addStatusState.pageState == PageState.loading)
                        const CircularProgressIndicatorWidget(),
                    ],
                  ),
          );
        },
      );
}
