import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirestoreContactUsService implements FirebaseFirestoreService {
  @override
  Future<void> createAnIssue(ContactUsDetails contactUsDetails) async {
    await getContactUsDetailsCollectionReference().add(
      contactUsDetails,
    );
    NetworkListeners.listener.add(
      Listener(
        ListenersFor.contacts,
        ListenersType.write,
        contactUsDetails.calculateSize,
      ),
    );
  }
}
