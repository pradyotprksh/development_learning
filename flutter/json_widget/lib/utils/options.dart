class Options {
  Options({required this.onTextChanged, required this.onButtonTap});

  final Function(String, String)? onTextChanged;
  final Function(String)? onButtonTap;
}