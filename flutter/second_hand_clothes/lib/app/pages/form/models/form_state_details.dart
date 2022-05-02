/// A form state details for an form item.
class FormItemStateDetails {
  /// [itemId] = Id of the item
  ///
  /// [errorMessage] = Error message for that particular item
  ///
  /// [textFieldValue] = Value of the item which is a text field
  ///
  /// [validators] = Set of validators which are used to validate the
  /// state of an item.
  FormItemStateDetails({
    required this.itemId,
    required this.textFieldValue,
    this.currentErrorMessage,
    this.errorMessage,
    this.validators,
  });

  final String itemId;
  final String? currentErrorMessage;
  final String? errorMessage;
  final String textFieldValue;
  final List<String>? validators;

  FormItemStateDetails copyWith({
    String? itemId,
    String? currentErrorMessage,
    String? errorMessage,
    String? textFieldValue,
    List<String>? validators,
  }) =>
      FormItemStateDetails(
        itemId: itemId ?? this.itemId,
        currentErrorMessage: currentErrorMessage ?? this.currentErrorMessage,
        errorMessage: errorMessage ?? this.errorMessage,
        textFieldValue: textFieldValue ?? this.textFieldValue,
        validators: validators ?? this.validators,
      );
}
