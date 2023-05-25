import json

try:
    print("Fetching translation file...")
    translation_file = open("./app/src/main/assets/localization_en.json")
    translation_str = translation_file.read()
    translation_dict = json.loads(translation_str)

    print("Creating class structure...")
    translation_class_str = """package com.pradyotprakash.postscomments.app.localization

object TR {
    {values}
}
"""

    print("Creating translation values...")
    translation_values = ""
    for key in translation_dict.keys():
        translation_values += f"""var {key} = Translation.getString("{key}")
    """

    print("Appending translation values into the class...")
    final_translation_class = translation_class_str.replace("{values}", translation_values)

    print("Adding class final result into kotlin file...")
    translation_kotlin_file = "./app/src/main/java/com/pradyotprakash/postscomments/app/localization/TR.kt"
    with open(translation_kotlin_file, mode="w") as f:
        f.write(final_translation_class)

    print("Done creating translation file...")
except Exception as e:
    print(e)
