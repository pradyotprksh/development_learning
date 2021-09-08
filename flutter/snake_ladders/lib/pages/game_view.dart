import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:snake_ladders/pages/game_controller.dart';

/// A view which will help in showing the UI to the user.
class GameView extends StatelessWidget {
  const GameView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Get.theme.backgroundColor,
      body: SafeArea(
        child: GetBuilder<GameController>(
          builder: (_controller) => Column(
            children: [
              GridView.builder(
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 10,
                  crossAxisSpacing: 1.0,
                  mainAxisSpacing: 1.0,
                ),
                shrinkWrap: true,
                itemCount: 100,
                itemBuilder: (_, count) {
                  return Box(
                    showPug: _controller.pugPosition == 0
                        ? 0 == count
                        : _controller.pugPosition - 1 == count,
                    showSnakeTail: _controller.snakeTailPosition - 1 == count,
                    showSnakeHead: _controller.snakeHeadPosition - 1 == count,
                  );
                },
              ),
              SizedBox(
                height: 30,
              ),
              GestureDetector(
                onTap: _controller.rollDice,
                child: Container(
                  color: Get.theme.primaryColor,
                  height: 50,
                  child: Center(
                    child: Text(
                      _controller.diceNumberString,
                      style: Get.textTheme.headline3?.copyWith(
                        color: Colors.white,
                      ),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 30,
              ),
              Text(
                _controller.lastGameStats,
                style: Get.textTheme.bodyText1,
              )
            ],
          ),
        ),
      ),
    );
  }
}

class Box extends StatelessWidget {
  const Box({
    Key? key,
    this.isBlackColor = false,
    this.showPug = false,
    this.showSnakeHead = false,
    this.showSnakeTail = false,
  }) : super(key: key);

  final bool isBlackColor;
  final bool showPug;
  final bool showSnakeHead;
  final bool showSnakeTail;

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.black,
      child: Center(
        child: Stack(
          children: [
            if (showSnakeHead)
              Icon(
                Icons.stop_circle,
                color: Colors.white,
              ),
            if (showSnakeTail)
              Icon(
                Icons.stop,
                color: Colors.white,
              ),
            if (showPug)
              Icon(
                Icons.accessibility,
                color: Colors.amber,
              ),
          ],
        ),
      ),
    );
  }
}
