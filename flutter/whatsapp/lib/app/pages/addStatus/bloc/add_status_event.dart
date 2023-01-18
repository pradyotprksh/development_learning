import 'package:equatable/equatable.dart';

abstract class AddStatusEvent extends Equatable {
  const AddStatusEvent();

  @override
  List<Object?> get props => [];
}

class UpdateBackgroundColor extends AddStatusEvent {
  const UpdateBackgroundColor({
    required this.newBackgroundColor,
  });

  final int newBackgroundColor;

  @override
  List<Object?> get props => [newBackgroundColor];
}

class UpdateFontFamily extends AddStatusEvent {
  const UpdateFontFamily({
    required this.newFont,
  });

  final String newFont;

  @override
  List<Object?> get props => [newFont];
}

class UploadStatus extends AddStatusEvent {
  const UploadStatus(this.statusValue);

  final String statusValue;

  @override
  List<Object?> get props => [statusValue];
}
