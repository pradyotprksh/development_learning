import 'dart:convert';

abstract class UtilMethods {
  static (bool, Exception?) isAValidJson(String jsonValue) {
    try {
      jsonDecode(jsonValue);
      return (true, null);
    } catch (e) {
      if (e is Exception) {
        return (false, e);
      }
      return (false, null);
    }
  }
}