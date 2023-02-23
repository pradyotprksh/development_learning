import 'dart:async';

import 'package:whatsapp/application/core/core.dart';
import 'package:whatsapp/application/domain/domain.dart';

mixin FirestoreContactsService implements FirebaseFirestoreService {
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

    NetworkListeners.listener.add(
      Listener(
        ListenersFor.contacts,
        ListenersType.write,
        contactsAvailableDetails.calculateSize,
      ),
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

          NetworkListeners.listener.add(
            Listener(
              ListenersFor.contacts,
              ListenersType.read,
              doc.data().size,
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
            (event) => event.docs.map(
              (e) {
                final data = e.data();
                NetworkListeners.listener.add(
                  Listener(
                    ListenersFor.contacts,
                    ListenersType.read,
                    data.size,
                  ),
                );
                return data;
              },
            ).toList(),
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
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.contacts,
          ListenersType.write,
          contactsNotAvailableDetails.calculateSize,
        ),
      );
    }
  }

  @override
  Future<bool> isContactsAvailableListPresent(String userId) async {
    final details =
        await getContactAvailableDetailsCollectionReference(userId).get();
    for (var element in details.docs) {
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.contacts,
          ListenersType.read,
          element.data().size,
        ),
      );
    }
    return details.size > 0;
  }

  @override
  Future<bool> isContactsNotAvailableListPresent(String userId) async {
    final details =
        await getContactNotAvailableDetailsCollectionReference(userId).get();
    for (var element in details.docs) {
      NetworkListeners.listener.add(
        Listener(
          ListenersFor.contacts,
          ListenersType.read,
          element.data().size,
        ),
      );
    }
    return details.size > 0;
  }
}
