import 'dart:collection';

class Graphs {
  var numberOfNodes = 0;
  var adjacentList = <int, List<int>>{};

  void addVertex(int node) {
    adjacentList[node] = <int>[];
    ++numberOfNodes;
  }

  void addConnection(int node1, int node2) {
    if (!adjacentList.containsKey(node1)) {
      addVertex(node1);
    }
    if (!adjacentList.containsKey(node2)) {
      addVertex(node2);
    }
    if (adjacentList[node1]?.contains(node2) ?? false) {
      print('Connection already added');
      return;
    }
    if (adjacentList[node2]?.contains(node1) ?? false) {
      print('Connection already added');
      return;
    }
    adjacentList[node1]?.add(node2);
    adjacentList[node2]?.add(node1);
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

  void bfsTraversal(int start) {
    var list = <int>[];
    var visited = List.filled(numberOfNodes, false);
    var queue = Queue<int>();

    visited[start] = true;
    queue.add(start);

    while (queue.isNotEmpty) {
      var s = queue.removeFirst();
      list.add(s);
      var connectionList = adjacentList[s];
      if (connectionList != null) {
        for (var connection in connectionList) {
          if (!visited[connection]) {
            visited[connection] = true;
            queue.add(connection);
          }
        }
      }
    }

    print(list);
  }
}
