import 'package:equatable/equatable.dart';
import 'package:whatsapp/app/app.dart';

class ContactUsState extends Equatable {
  const ContactUsState({
    this.pageState = PageState.idle,
    this.shareDeviceInformation = true,
  });

  ContactUsState copyWith({
    PageState? pageState,
    bool? shareDeviceInformation,
  }) =>
      ContactUsState(
        pageState: pageState ?? this.pageState,
        shareDeviceInformation:
            shareDeviceInformation ?? this.shareDeviceInformation,
      );

  final PageState pageState;
  final bool shareDeviceInformation;

  @override
  List<Object?> get props => [
        pageState,
        shareDeviceInformation,
      ];
}
