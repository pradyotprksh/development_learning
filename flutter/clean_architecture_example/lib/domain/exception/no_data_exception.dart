class PetPerfectException implements Exception {
  PetPerfectException();
}

/// An exception which will be thrown when there is no data available
class NoDataException implements PetPerfectException {
  NoDataException({
    this.message = 'No data found',
  });

  String message;

  @override
  String toString() => message;
}
