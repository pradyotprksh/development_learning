import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:whatsapp/app/app.dart';

class UtilitiesBloc extends Bloc<UtilitiesEvent, UtilitiesState> {
  UtilitiesBloc() : super(const UtilitiesState()) {
    on<InitiateConnectivityCheck>(_onConnectivityCheck);
  }

  void _onConnectivityCheck(
    InitiateConnectivityCheck event,
    Emitter<UtilitiesState> emit,
  ) async {
    await emit.forEach(
      Connectivity().onConnectivityChanged,
      onData: (connectionResult) => state.copyWith(
        connectivityResult: connectionResult,
      ),
    );
  }
}
