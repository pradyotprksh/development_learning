import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:whatsapp/app/app.dart';
import 'package:whatsapp/domain/models/user.dart';

class CreateGroupWidget extends StatefulWidget {
  const CreateGroupWidget({
    super.key,
    required this.users,
    required this.onSubmit,
  });

  final List<UserDetails> users;
  final void Function(String groupName, String groupImagePath) onSubmit;

  @override
  State<CreateGroupWidget> createState() => _CreateGroupWidgetState();
}

class _CreateGroupWidgetState extends State<CreateGroupWidget> {
  final _formKey = GlobalKey<FormState>();
  final _groupNameController = TextEditingController();
  var imagePath = '';

  void _submitForm(BuildContext context) {
    var isFormValid = _formKey.currentState?.validate() == true;
    if (isFormValid) {
      widget.onSubmit(_groupNameController.text, imagePath);
      context.navigator.pop();
    }
  }

  @override
  Widget build(BuildContext context) => SizedBox(
        height: context.mediaQuery.size.height * 0.8,
        child: Scaffold(
          backgroundColor: Colors.transparent,
          appBar: AppBar(
            leading: const CloseButton(),
            elevation: 0,
            backgroundColor: Colors.transparent,
            title: Text(
              context.translator.enterGroupDetails,
            ),
          ),
          floatingActionButton: FloatingActionButton(
            onPressed: () {
              _submitForm(context);
            },
            child: const Icon(
              Icons.check,
            ),
          ),
          body: Form(
            key: _formKey,
            child: ListView(
              primary: false,
              children: [
                Padding(
                  padding: ThemeEdgeInsets.all15,
                  child: Row(
                    children: [
                      ImagePickerWidget(
                        image: (path) {
                          setState(() {
                            imagePath = path;
                          });
                        },
                        cropStyle: CropStyle.circle,
                        aspectRatioPresets: const [
                          CropAspectRatioPreset.square,
                        ],
                        child: CachedNetworkImageWidget(
                          imageUrl: '',
                          placeholder: imagePath.isNotEmpty
                              ? CircleAvatar(
                                  radius: 30,
                                  backgroundColor:
                                      context.themeData.primaryColor,
                                  backgroundImage: FileImage(
                                    File(
                                      imagePath,
                                    ),
                                  ),
                                )
                              : CircleAvatar(
                                  radius: 30,
                                  backgroundColor:
                                      context.themeData.primaryColor,
                                  backgroundImage: const AssetImage(
                                    AssetsPath.defaultAvatar,
                                  ),
                                ),
                          height: 60,
                          width: 60,
                          clipToCircle: true,
                        ),
                      ),
                      ThemeSizedBox.width15,
                      Flexible(
                        child: TextFormField(
                          controller: _groupNameController,
                          decoration: InputDecoration(
                            label: Text(
                              context.translator.groupName,
                            ),
                          ),
                          validator: (groupName) =>
                              AppUtilsMethods.lengthShouldBeGraterThan(
                            groupName,
                            5,
                            context.translator.groupNameInvalid,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                ThemeSizedBox.height10,
                Padding(
                  padding: ThemeEdgeInsets.all15,
                  child: Text(
                    '${widget.users.length} ${context.translator.participants}',
                  ),
                ),
                SelectedUsersWidget(
                  selectedUserDetails: widget.users,
                ),
              ],
            ),
          ),
        ),
      );
}
