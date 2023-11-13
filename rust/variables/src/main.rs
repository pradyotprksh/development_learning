fn main() {
    let x = 5;
    println!("X valus is {x}");
    // x = 12; - Error: By default variables are immutable in rust
    let mut x = 5;
    println!("mutable X valus is {x}");
    x = 12;
    println!("mutable X valus is {x}");

    const SECONDS_IN_FIVE_MINUTES: u32 = 5 * 60;
    println!("{SECONDS_IN_FIVE_MINUTES}");

    let x = 5;
    let x = x + 1;
    {
        let x = x * 2;
        println!("The value of x in the inner scope is: {x}");
    }
    println!("The value of x is: {x}");

    let guess: u32 = "42".parse().expect("Not a number!");
    let x = 2.0;
    let y: f32 = 3.0;
    println!("guess {}, x {}, y {}", guess, x, y);

    let sum = 5 + 10;
    let difference = 95.5 - 4.3;
    let product = 4 * 30;
    let quotient = 56.7 / 32.2;
    let truncated = -5 / 3;
    let remainder = 43 % 5;
    println!("sum {}, difference {}, product {}, quotient {}, truncated {}, remainder {}", sum, difference, product, quotient, truncated, remainder);

    let t = true;
    let f: bool = false;
    println!("t {}, f {}", t, f);

    let c = 'z';
    let z: char = 'ℤ';
    let heart_eyed_cat = '😻';
    println!("c {}, z {}, heart_eyed_cat {}", c, z, heart_eyed_cat);

    let tup: (i32, f64, u8) = (500, 6.4, 1);
    let (x, y, z) = tup;
    println!("The value of x is: {x}");
    println!("The value of y is: {y}");
    println!("The value of z is: {z}");
    let five_hundred = tup.0;
    let six_point_four = tup.1;
    let one = tup.2;
    println!("five_hundred {}, six_point_four {}, one {}", five_hundred, six_point_four, one);

    let a = [1, 2, 3, 4, 5];
    println!("{}", a[0]);
    let months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    println!("{}", months[4]);
    let a: [i32; 5] = [1, 2, 3, 4, 5];
    println!("{}", a[3]);
    let a = [3; 5];
    println!("{}", a[4]);
}
