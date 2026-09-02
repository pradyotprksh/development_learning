fn print_elements(elements: &[String]) {
    // for element in elements {
    //     println!("{}", element)
    // }

    // elements.iter().for_each(|element| println!("{}", element));

    elements.iter().map(|element| format!("{} {}", element, element)).for_each(|element| println!("{}", element));
}

fn shorten_strings(elements: &mut [String]) {
    elements.iter_mut().for_each(|ele| ele.truncate(1));
}

fn to_uppercase(elements: &[String]) -> Vec<String> {
    elements.iter().map(|ele| ele.to_uppercase()).collect()
}

fn move_elements(vec_a: Vec<String>, vec_b: &mut Vec<String>) {
    vec_a.into_iter().for_each(|ele| vec_b.push(ele));
}

fn explode(elements: &[String]) -> Vec<Vec<String>> {
    elements.iter().map(|ele| ele.chars().map(|ele| ele.to_string()).collect()).collect()
}

fn find_color_or(elements: &[String], search: &str, fallback: &str) -> String {
    elements
        .iter()
        .find(|ele| ele.contains(search))
        .map_or(String::from(fallback), |ele| ele.to_string())
}

fn main() {
    let mut colors = vec![
        String::from("red"),
        String::from("green"),
        String::from("blue"),
    ];

    print_elements(&colors);
    print_elements(&colors[1..3]);

    let uppercase = to_uppercase(&colors);
    print_elements(&uppercase);

    shorten_strings(&mut colors);

    print_elements(&colors);

    let mut destination = vec![];
    move_elements(colors, &mut destination);

    print_elements(&destination);

    let explode = explode(&destination);
    println!("{:#?}", explode);

    let found_color = find_color_or(&destination, "r", "orange");
    println!("{:#?}", found_color);
}
