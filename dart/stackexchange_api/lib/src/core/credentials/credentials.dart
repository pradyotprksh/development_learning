/// Represents the credentials of [stackapps](https://stackapps.com).
///
/// Required [clientId] is identifies your application to the Stack
/// Exchange API. Your application client id is not secret, and may
/// be safely embedded in distributed binaries.
///
/// [clientSecret] required if your app uses the explicit path.
///
/// Use [key] when making requests against the Stack Exchange API to receive
/// a higher request quota.
///
/// For example,
/// ```dart
/// Credentials credentials = Credentials(
///   clientId: 12345,
///   clientSecret: "xxx",
///   key: "xxx",
/// );
/// ```
/// or
/// ```dart
/// Credentials credentials = Credentials(
///   clientId: 12345,
///   clientSecret: "xxx",
/// );
/// ```
/// or
/// ```dart
/// Credentials credentials = Credentials(
///   clientId: 12345,
/// );
/// ```
class Credentials {
  int clientId;

  String? clientSecret;

  String? key;

  Credentials({
    required this.clientId,
    this.clientSecret,
    this.key,
  });
}
