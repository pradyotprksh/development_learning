use std::{fs};
use std::io::Error;

fn extract_errors(text: &str) -> Vec<&str> {
    let split_text = text.split("\n");

    let mut results = vec![];

    for line in split_text {
        if line.starts_with("ERROR") {
            results.push(line);
        }
    }

    results
}

fn main() -> Result<(), Error> {
    let text = fs::read_to_string("logs.txt")?;
    let error_logs = extract_errors(&text);
    fs::write("errors.txt", error_logs.join("\n"))?;

    Ok(())

    // let text = fs::read_to_string("logs.txt").expect("failed to read logs.txt");
    // let error_logs = extract_errors(&text);
    // fs::write("errors.txt", error_logs.join("\n")).expect("failed to write erros.txt");

    // match fs::read_to_string("logs.txt") {
    //     Ok(text) => {
    //         let error_logs = extract_errors(&text);
            
    //         match fs::write("errors.txt", error_logs.join("\n")) {
    //             Ok(..) => {
    //                 println!("Wrote errors to errors.text")
    //             }
    //             Err(error) => {
    //                 println!("Failed to write file: {}", error)
    //             }
    //         }
    //     }
    //     Err(error) => {
    //         println!("Failed to read file: {}", error)
    //     }
    // }
}
