mod content;

use content::media::Media;
use content::catalog::Catalog;

fn main() {
    let audiobook = Media::Audiobook { title: String::from("An Audiobook") };
    let good_movie = Media::Movie { title: String::from("Good Movie"), director: String::from("Good Director") };
    let bad_book = Media::Book { title: String::from("Bad Book"), author: String::from("Bad Author") };
    let podcast = Media::Podcast(12);
    let placeholder = Media::Placeholder;

    let mut catalog = Catalog::new();
    catalog.add(audiobook);
    catalog.add(good_movie);
    catalog.add(bad_book);
    catalog.add(podcast);
    catalog.add(placeholder);
 
    println!("{:#?}", catalog );

    let item = catalog.get_by_index(0);

    match item {
        Some(value) => {
            println!("Found {:#?}", item);
        }
        None => {
            println!("Not found any media");
        }
    }

    println!("{:#?}", item.unwrap());
    println!("{:#?}", item.expect("Expected there to be an item"));
    println!("{:#?}", item.unwrap_or(&Media::Placeholder));
}
