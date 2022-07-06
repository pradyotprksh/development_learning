import 'package:equatable/equatable.dart';

/// A form event class which will listen to any display events made by the
/// display screen.
abstract class DisplayEvent extends Equatable {
  const DisplayEvent();

  @override
  List<Object?> get props => [];
}

/// A child of [DisplayEvent] which will trigger when the fetch event.
class FetchFiles extends DisplayEvent {
  const FetchFiles();
}

/// A child of [DisplayEvent] which will trigger when the save image.
class SaveUserImage extends DisplayEvent {
  const SaveUserImage();
}

/// A child of [DisplayEvent] which will trigger when the file is loaded.
class FileLoaded extends DisplayEvent {
  const FileLoaded();
}
