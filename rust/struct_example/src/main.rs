struct User {
    active: bool,
    username: String,
    email: String,
    sign_in_count: u64
}

fn main() {
    let user1 = User {
        active: true,
        email: String::from("randomemail@example.com"),
        username: String::from("username1"),
        sign_in_count: 1
    };
    
    println!("active: {}", user1.active);
    println!("username: {}", user1.username);
    println!("email: {}", user1.email);
    println!("sign_in_count: {}", user1.sign_in_count);

    // cannot mutate immutable variable `user1 - need the whole 
    // variable mutable to change a value
    // user1.email = String::from("anotheremail@example.com");

    let mut user2 = User {
        active: true,
        email: String::from("randomemail@example.com"),
        username: String::from("username1"),
        sign_in_count: 1
    };
    println!("email: {}", user2.email);
    user2.email = String::from("randomemail2@example.com");
    println!("email: {}", user2.email);

    let new_user = build_user(String::from("newemail@example.com"), String::from("newusername"));
    println!("New user {}", new_user.email);
}

fn build_user(email: String, username: String) -> User {
    User {
        active: true,
        username: username,
        email: email,
        sign_in_count: 1,
    }
}
