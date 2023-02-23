import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:equatable/equatable.dart';

class UtilitiesState extends Equatable {
  const UtilitiesState({
    this.connectivityResult = ConnectivityResult.wifi,
  });

  UtilitiesState copyWith({
    ConnectivityResult? connectivityResult,
  }) =>
      UtilitiesState(
        connectivityResult: connectivityResult ?? this.connectivityResult,
      );

  final ConnectivityResult connectivityResult;

  bool isNetworkAvailable() =>
      connectivityResult == ConnectivityResult.mobile ||
      connectivityResult == ConnectivityResult.wifi;

  @override
  List<Object?> get props => [
        connectivityResult,
      ];
}
