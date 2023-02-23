import 'package:equatable/equatable.dart';
import 'package:whatsapp/application/app/app.dart';
import 'package:whatsapp/application/domain/domain.dart';

class DirectMessageState extends Equatable {
  const DirectMessageState({
    this.userDetails,
    this.directMessageDetails,
    this.pageState = PageState.idle,
    this.isEmojiOptionVisible = false,
    this.messages = const [],
    this.attachments = const [],
    this.uploadingFile,
  });

  DirectMessageState copyWith({
    UserDetails? userDetails,
    PageState? pageState,
    DirectMessageDetails? directMessageDetails,
    bool? isEmojiOptionVisible,
    List<SingleMessageDetails>? messages,
    List<FileInformationDetails>? attachments,
    FileInformationDetails? uploadingFile,
  }) =>
      DirectMessageState(
        userDetails: userDetails ?? this.userDetails,
        pageState: pageState ?? this.pageState,
        directMessageDetails: directMessageDetails ?? this.directMessageDetails,
        isEmojiOptionVisible: isEmojiOptionVisible ?? this.isEmojiOptionVisible,
        messages: messages ?? this.messages,
        attachments: attachments ?? this.attachments,
        uploadingFile: uploadingFile,
      );

  final UserDetails? userDetails;
  final DirectMessageDetails? directMessageDetails;
  final PageState pageState;
  final bool isEmojiOptionVisible;
  final List<SingleMessageDetails> messages;
  final List<FileInformationDetails> attachments;
  final FileInformationDetails? uploadingFile;

  @override
  List<Object?> get props => [
        userDetails,
        pageState,
        directMessageDetails,
        isEmojiOptionVisible,
        messages,
        attachments,
        uploadingFile,
      ];
}
