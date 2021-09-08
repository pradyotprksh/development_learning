import 'dart:math';

import 'package:get/get.dart';

/// A controller to handle the business logic and update the UI.
class GameController extends GetxController {
  var pugPosition = 100;
  var snakeHeadPosition = 0;
  var snakeTailPosition = 0;
  var diceNumber = 0;
  var diceNumberString = 'Start game';
  var lastGameStats = '';
  var numberOfMoves = 0;
  var rng = new Random();

  void _initialSetup() {
    pugPosition = 100;
    diceNumber = 0;
    numberOfMoves = 0;
    diceNumberString = 'Start game';
    _updateSnakePosition();
  }

  @override
  void onInit() {
    _initialSetup();
    super.onInit();
  }

  void rollDice() {
    // Generate random number up to 6
    var num = rng.nextInt(6);
    // If 0 then 1
    if (num <= 0) {
      num = 1;
    }
    ++numberOfMoves;
    // If pug is within the board range
    if (pugPosition - num >= 1) {
      // If game started
      if (diceNumber == 0) {
        // If not even then try again
        if (num % 2 != 0) {
          diceNumberString = 'Try again';
        } else {
          diceNumber = num;
          diceNumberString = '$diceNumber';
        }
      } else {
        diceNumber = num;
        diceNumberString = '$diceNumber';
      }
      // If even number came
      if (diceNumberString != 'Try again') {
        pugPosition -= diceNumber;
        if (pugPosition == snakeHeadPosition) {
          pugPosition = snakeTailPosition;
        }
      }
      // If pug reached the first box
      if (pugPosition == 1) {
        diceNumberString = 'Finished';
      }
    } else if (pugPosition == 1) {
      lastGameStats = 'Last game completed in $numberOfMoves moves';
      _initialSetup();
      _updateSnakePosition();
    } else {
      diceNumberString = 'Try again';
    }
    update();
  }

  void _updateSnakePosition() {
    snakeHeadPosition = rng.nextInt(50);
    snakeTailPosition = snakeHeadPosition + 10 + rng.nextInt(35);
    update();
  }
}
