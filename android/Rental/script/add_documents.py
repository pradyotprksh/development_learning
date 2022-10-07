# TODO Need to work on this, not completed yet

import os
import re

print("Fetching all the kotlin files...")

kotlin_files = []

for folder, subFolders, files in os.walk(os.pardir):
    for file in files:
        last_three_char = file.title()[-3:]
        if last_three_char == ".Kt":
            kotlin_files.append(os.path.join(folder, file))

print(f"Found {kotlin_files.__len__()} kotlin files in the entire project...")

print("Documentation for each function started...")
for file in kotlin_files:
    print("**********************************************")
    print(f"Checking {file.title()} file for functions...")
    with open(file, mode="r") as f:
        prev_file_content = f.read()

    with open(file, mode="r") as f:
        new_file_content = ""
        prev_line = ""

        for line in f.readlines():
            match = re.search(r"\s(fun)\s[\s\S\w\W]*", line)
            if match and "override" not in line and "*/" not in prev_line:
                start_index = match.start()
                end_index = match.end()

                first_parenthesis = line.find("(")
                fun_name = line[start_index + 5:first_parenthesis]

                doc_comment = """    /**
     * Name: {name}
     *
     * {parameters}
"""
                # Add function name
                doc_comment = doc_comment.replace("{name}", fun_name)

                # Add parameters
                fun_index = prev_file_content.find(line)
                content_after_functions = prev_file_content[fun_index:]
                last_parenthesis = content_after_functions.find(")")
                whole_function_declaration = content_after_functions[:last_parenthesis + 1]
                start_parameter_index = whole_function_declaration.find("(") + 1
                end_parameter_index = whole_function_declaration.find(")")
                only_parameter_content = whole_function_declaration[start_parameter_index:end_parameter_index]\
                    .strip().replace(" ", "").replace("\n", "")
                parameters = only_parameter_content.split(",")
                parameters_name = ""
                for parameter in parameters:
                    if ":" in parameter:
                        name_type = parameter.split(":")
                        if len(parameters_name) == 0:
                            parameters_name = f"\n     *\n     * @param {name_type[0]} Of type {name_type[1]} = "
                        else:
                            parameters_name = parameters_name + "\n" + f"     * @param {name_type[0]} Of type {name_type[1]} = "

                if len(parameters_name) == 0:
                    doc_comment = doc_comment.replace("{parameters}", "")
                else:
                    doc_comment = doc_comment.replace("{parameters}", parameters_name)

                # Close the doc comment
                doc_comment += "     */\n"

                whole_new_line = doc_comment + line
                new_file_content += whole_new_line
            else:
                new_file_content += line

            prev_line = line

        with open(file, mode="w") as w:
            w.write(new_file_content)

    print(f"{file.title()} file updated...")
    print("**********************************************")
