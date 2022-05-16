/// An exception which will be thrown in the domain layer. Every exception
/// will be converted to this so that the app layer knows its from
/// the domain layer.
class DomainException implements Exception {
  const DomainException({
    this.message,
  });

  final String? message;
}

/// A child of [DomainException] which will be thrown during an authentication
/// process.
class AuthenticationException extends DomainException {
  const AuthenticationException({
    super.message,
  });

  @override
  String toString() => 'AuthenticationException: $message';
}
