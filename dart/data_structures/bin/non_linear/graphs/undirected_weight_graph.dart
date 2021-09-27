class UndirectedWeightGraph {
  var numberOfNodes = 0;
  var adjacentList = <int, List<Map<int, int>>>{};

  void addVertex(int node) {
    adjacentList[node] = [];
    ++numberOfNodes;
  }

  void addConnection(int from, int to, int weight) {
    if (!adjacentList.containsKey(from)) {
      addVertex(from);
    }
    if (!adjacentList.containsKey(to)) {
      addVertex(to);
    }
    var connectionsFrom = adjacentList[from];
    if (connectionsFrom != null) {
      for (var connections in connectionsFrom) {
        if (connections.containsKey(to)) {
          var weightFrom = connections[to];
          print(
              'A connection already exists from $from to $to with weight $weightFrom');
          return;
        }
      }
    }

    var connectionsTo = adjacentList[to];
    if (connectionsTo != null) {
      for (var connections in connectionsTo) {
        if (connections.containsKey(from)) {
          var weightTo = connections[from];
          print(
              'A connection already exists from $to to $from with weight $weightTo');
          return;
        }
      }
    }

    adjacentList[from]?.add(<int, int>{to: weight});
    adjacentList[to]?.add(<int, int>{from: weight});
  }

  void printConnections() {
    for (var node in adjacentList.keys) {
      var connections = '';
      var connectionList = adjacentList[node];
      if (connectionList != null) {
        for (var connection in connectionList) {
          connections = '$connections $connection';
        }
      }
      print('$node ---> $connections');
    }
  }

  void dijkstraAlgorithm(int source) {}
}
