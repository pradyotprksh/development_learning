import 'dart:async';

import 'package:equatable/equatable.dart';

enum ListenersFor {
  file,
  videoCall,
  phoneCall,
  user,
  status,
  security,
  savedMessage,
  calls,
  contacts,
  directMessages,
  groupMessages,
  singleMessage,
}

enum ListenersType {
  read,
  write,
}

class Listener extends Equatable {
  const Listener(this.listenersFor, this.listenersType, this.size);

  final ListenersFor listenersFor;
  final ListenersType listenersType;
  final double size;

  @override
  List<Object?> get props => [
        listenersFor,
        listenersType,
        size,
      ];
}

abstract class NetworkListeners {
  static final StreamController<Listener> listener = StreamController();
}
