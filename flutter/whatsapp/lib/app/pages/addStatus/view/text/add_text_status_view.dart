import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_colorpicker/flutter_colorpicker.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/domain.dart';

class AddTextStatusView extends StatelessWidget {
  AddTextStatusView({super.key});

  void _submitStatus(
      BuildContext context, SingleMessageDetails? messageDetails) {
    if (_formKey.currentState?.validate() == true) {
      context.read<AddStatusBloc>().add(
            UploadStatus(
              _statusController.text,
              messageDetails: messageDetails,
            ),
          );
    }
  }

  String? _validateStatus(String? value, String errorMessage) {
    if (value != null) {
      if (value.isEmpty) {
        return errorMessage;
      }
    }
    return null;
  }

  final _formKey = GlobalKey<FormState>();
  final _statusController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    final messageDetails =
        context.routeSettings?.arguments as SingleMessageDetails?;
    if (messageDetails != null) {
      _statusController.text = messageDetails.message;
    }

    return BlocConsumer<AddStatusBloc, AddStatusState>(
      listener: (_, addStatusState) {
        if (addStatusState.pageState == PageState.success) {
          context.navigator.pop();
        }
      },
      builder: (_, addStatusState) => Scaffold(
        backgroundColor: Color(addStatusState.chosenColor),
        appBar: AppBar(
          leading: const CloseButton(),
          actions: [
            IconButton(
              onPressed: () async {
                final addStatusBloc = context.read<AddStatusBloc>();
                final font = await PersonaliseUtils.getFont(
                  context,
                  addStatusState.currentFontFamily,
                );
                addStatusBloc.add(
                  UpdateFontFamily(newFont: font),
                );
              },
              icon: const Icon(
                Icons.text_fields,
              ),
            ),
            IconButton(
              onPressed: () {
                showDialog<void>(
                  context: context,
                  builder: (_) => AlertDialog(
                    content: SingleChildScrollView(
                      child: BlockPicker(
                        pickerColor: Color(addStatusState.chosenColor),
                        onColorChanged: (color) {
                          context.read<AddStatusBloc>().add(
                                UpdateBackgroundColor(
                                  newBackgroundColor: color.value,
                                ),
                              );
                          context.navigator.pop();
                        },
                      ),
                    ),
                  ),
                );
              },
              icon: const Icon(
                Icons.color_lens,
              ),
            ),
          ],
        ),
        body: Stack(
          alignment: Alignment.center,
          children: [
            Column(
              mainAxisSize: MainAxisSize.max,
              children: [
                const Spacer(),
                Padding(
                  padding: ThemeEdgeInsets.all20,
                  child: Form(
                    key: _formKey,
                    child: TextFormField(
                      controller: _statusController,
                      decoration: InputDecoration(
                        hintText: context.translator.typeAStatus,
                        hintStyle: GoogleFonts.getFont(
                          addStatusState.currentFontFamily,
                        ),
                      ),
                      validator: (value) => _validateStatus(
                        value,
                        context.translator.statusErrorMessage,
                      ),
                      maxLength: 700,
                      maxLines: 10,
                      autofocus: true,
                      style: GoogleFonts.getFont(
                        addStatusState.currentFontFamily,
                      ),
                    ),
                  ),
                ),
                const Spacer(),
                UploadStatusWidget(
                  onPressed: () {
                    _submitStatus(context, messageDetails);
                  },
                ),
              ],
            ),
            if (addStatusState.pageState == PageState.loading)
              const CircularProgressIndicatorWidget(),
          ],
        ),
      ),
    );
  }
}
