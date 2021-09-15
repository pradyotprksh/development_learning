class CircularQueue {
  final _queue = List.generate(5, (index) => -1);
  var _rear = -1;
  var _front = -1;

  void enqueue(int value) {
    if (isFull()) {
      print('Queue full can\'t add $value');
    } else {
      ++_rear;
      if (_rear >= _queue.length) {
        _rear = 0;
      }
      if (_front == -1) {
        ++_front;
      }
      _queue[_rear] = value;
      print('Added $value');
      printQueue();
    }
  }

  void dequeue() {
    if (isEmpty()) {
      print('Queue empty');
    } else {
      _queue[_front] = -1;
      ++_front;
      if (_front == _rear) {
        _front = -1;
        _rear = -1;
      }
      print('Removed element');
      printQueue();
    }
  }

  bool isEmpty() {
    return _front == -1 && _rear == -1;
  }

  bool isFull() {
    if (_rear + 1 >= _queue.length) {
      return _front == 0;
    } else {
      return _rear + 1 == _front;
    }
  }

  void peek() {
    print('Top - ${_queue[_front]}');
  }

  void printQueue() {
    var elements = '';
    for (var i in _queue) {
      if (i == -1) {
        elements = '$elements#--->';
      } else {
        elements = '$elements$i--->';
      }
    }
    print(elements);
  }
}
