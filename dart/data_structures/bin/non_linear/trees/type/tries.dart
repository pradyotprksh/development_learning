class TriesNode {
  String value = "";
  var children = <String, TriesNode>{};
  bool isLast = false;

  @override
  String toString() {
    if (isLast) {
      return '$value -> $children -> *';
    }
    return '$value -> $children';
  }
}

class Tries {
  var root = TriesNode();

  void insert(String value) {
    var children = root.children;
    var s = value.split('');
    TriesNode node;
    for (var i = 0; i < value.length; i++) {
      if (children.containsKey(s[i])) {
        node = children[s[i]]!;
      } else {
        node = TriesNode()..value = s[i];
        children[s[i]] = node;
      }
      children = node.children;

      if (i == value.length - 1) {
        node.isLast = true;
      }
    }

    print(root.toString());
  }

  void search(String value) {
    var node = _searchNode(value);
    if (node != null) {
      print('Found $value');
    } else {
      print('Not found $value');
    }
  }

  TriesNode? _searchNode(String value) {
    var children = root.children;
    var s = value.split('');
    TriesNode? node;
    for (var i = 0; i < value.length; i++) {
      if (children.containsKey(s[i])) {
        node = children[s[i]]!;
      } else {
        return null;
      }
      children = node.children;
    }
    return node;
  }
}
