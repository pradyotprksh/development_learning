import 'package:equatable/equatable.dart';
import 'package:whatsapp/domain/domain.dart';

class GroupMessageState extends Equatable {
  const GroupMessageState({
    this.groupMessageDetails,
    this.isEmojiOptionVisible = false,
    this.messages = const [],
    this.usersDetails = const [],
    this.attachments = const [],
    this.uploadingFile,
  });

  GroupMessageState copyWith({
    UsersGroupMessageDetails? groupMessageDetails,
    bool? isEmojiOptionVisible,
    List<SingleMessageDetails>? messages,
    List<UserDetails>? usersDetails,
    List<FileInformationDetails>? attachments,
    FileInformationDetails? uploadingFile,
  }) =>
      GroupMessageState(
        groupMessageDetails: groupMessageDetails ?? this.groupMessageDetails,
        isEmojiOptionVisible: isEmojiOptionVisible ?? this.isEmojiOptionVisible,
        messages: messages ?? this.messages,
        usersDetails: usersDetails ?? this.usersDetails,
        attachments: attachments ?? this.attachments,
        uploadingFile: uploadingFile,
      );

  final UsersGroupMessageDetails? groupMessageDetails;
  final bool isEmojiOptionVisible;
  final List<SingleMessageDetails> messages;
  final List<UserDetails> usersDetails;
  final List<FileInformationDetails> attachments;
  final FileInformationDetails? uploadingFile;

  @override
  List<Object?> get props => [
        groupMessageDetails,
        isEmojiOptionVisible,
        messages,
        usersDetails,
        attachments,
        uploadingFile,
      ];
}
