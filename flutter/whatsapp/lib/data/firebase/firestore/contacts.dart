import 'dart:async';

import 'package:whatsapp/core/core.dart';
import 'package:whatsapp/domain/domain.dart';

mixin FirebaseContactsService implements FirebaseFirestoreService {
  @override
  Future<void> setContactAvailableDetails(
    String userId,
    ContactsAvailableDetails contactsAvailableDetails,
  ) async {
    final updatedDetails = contactsAvailableDetails.updateDocumentReference(
      getUserCollectionReference().doc(contactsAvailableDetails.userId),
    );

    await getContactAvailableDetailsCollectionReference(userId)
        .doc(contactsAvailableDetails.userId)
        .set(
          updatedDetails,
        );
  }

  @override
  StreamController<List<UserContactsAvailableDetails>?>
      getUserContactsAvailable(
    String userId,
  ) {
    final userContactsAvailableDetails =
        StreamController<List<UserContactsAvailableDetails>?>();
    getContactAvailableDetailsCollectionReference(userId).snapshots().listen(
      (event) async {
        var contacts = <UserContactsAvailableDetails>[];
        for (var doc in event.docs) {
          final userDetails =
              await getUserCollectionReference().doc(doc.data().userId).get();
          contacts.add(
            UserContactsAvailableDetails(
              userDetails.data(),
              doc.data(),
              false,
            ),
          );
        }
        userContactsAvailableDetails.add(contacts);
      },
    );
    return userContactsAvailableDetails;
  }

  @override
  Stream<List<ContactsNotAvailableDetails>?> getUserContactsNotAvailable(
    String userId,
  ) =>
      getContactNotAvailableDetailsCollectionReference(userId).snapshots().map(
            (event) => event.docs.map((e) => e.data()).toList(),
          );

  @override
  Future<void> setContactNotAvailableDetails(
    String userId,
    ContactsNotAvailableDetails contactsNotAvailableDetails,
  ) async {
    if (contactsNotAvailableDetails.shouldAdd()) {
      await getContactNotAvailableDetailsCollectionReference(userId)
          .doc(
            contactsNotAvailableDetails.getDocId(),
          )
          .set(contactsNotAvailableDetails);
    }
  }

  @override
  Future<bool> isContactsAvailableListPresent(String userId) async {
    final details =
        await getContactAvailableDetailsCollectionReference(userId).get();
    return details.size > 0;
  }

  @override
  Future<bool> isContactsNotAvailableListPresent(String userId) async {
    final details =
        await getContactNotAvailableDetailsCollectionReference(userId).get();
    return details.size > 0;
  }
}
