import 'package:get/get.dart';
import 'package:snake_ladders/pages/game_controller.dart';

/// Binding for the game page
class GameBinding extends Bindings {
  @override
  void dependencies() {
    Get.put(GameController());
  }
}
