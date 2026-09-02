#[derive(Debug)]
enum Media {
    Book { title: String, author: String },
    Movie { title: String, director: String },
    Audiobook { title: String },
    Podcast(u32),
    Placeholder,
}

impl Media {
    fn description(&self) -> String {
        match self {
            Media::Book { title, author } => {
                format!("Book: {} {}", title, author)
            },
            Media::Movie { title, director } => {
                format!("Movie: {} {}", title, director)
            },
            Media::Audiobook { title } => {
                format!("Audiobook: {}", title)
            },
            Media::Podcast(episode_number) => {
                format!("Podcast: {}", episode_number)
            },
            Media::Placeholder => {
                format!("Placeholder")
            },
        }
    }
}

#[derive(Debug)]
struct Catalog {
    items: Vec<Media>,
}

impl Catalog {
    fn new() -> Self {
        Catalog { items: vec![] }
    }

    fn add(&mut self, media: Media) {
        println!("Adding {} to the catalog", media.description());
        self.items.push(media);
    }

    fn get_by_index(&self, index: usize) -> Option<&Media> {
        if self.items.len() > index {
            Some(&self.items[index])
        } else {
            None
        }
    }
}

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
