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

  void dijkstraAlgorithm(int source) {
    var visitedNodes = List.filled(numberOfNodes, false);
    var distance = List.filled(numberOfNodes, double.maxFinite.toInt());

    distance[source] = 0;
    for (var i = 0; i < numberOfNodes; i++) {
      var min = minIndex(distance, visitedNodes);
      visitedNodes[min] = true;

      var node = adjacentList[min];
      if (node != null) {
        for (var v = 0; v < numberOfNodes; v++) {
          var weight = 0;
          for (var connections in node) {
            if (connections.containsKey(v)) {
              weight = connections[v] ?? 0;
              break;
            }
          }

          if (!visitedNodes[v] &&
              weight != 0 &&
              (distance[min] + weight) < distance[v]) {
            distance[v] = distance[min] + weight;
          }
        }
      }
    }

    print(distance);
  }

  int minIndex(List<int> distances, List<bool> visited) {
    var minIndex = -1;
    var minValue = double.maxFinite.toInt();
    for (var d = 0; d < distances.length; d++) {
      if (!visited[d] && distances[d] < minValue) {
        minValue = distances[d];
        minIndex = d;
      }
    }
    return minIndex;
  }
}
