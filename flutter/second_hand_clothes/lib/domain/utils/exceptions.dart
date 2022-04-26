class DomainException implements Exception {
  const DomainException();
}

class AuthenticationException extends DomainException {
  const AuthenticationException({
    required this.message,
  });

  final String message;

  @override
  String toString() => 'AuthenticationException: $message';
}
