/// The type of request by which the format of the IP Address will be decided.
///
/// [text] : 98.207.254.136 or 2a00:1450:400f:80d::200e
/// [json] : {"ip":"98.207.254.136"} or {"ip":"2a00:1450:400f:80d::200e"}
enum RequestType {
  text,
  json,
}
