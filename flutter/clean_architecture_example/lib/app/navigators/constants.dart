/// A constant class for navigators, this will help in taking out the default
/// values from the navigator part and use it anywhere.
class NavigatorsConstants {
  factory NavigatorsConstants() => _instance;

  NavigatorsConstants._privateConstructor();

  static final NavigatorsConstants _instance =
      NavigatorsConstants._privateConstructor();

  final splash = '/splash';
  final displayRoute = '/display';
  final apiRoute = '/api';
}
