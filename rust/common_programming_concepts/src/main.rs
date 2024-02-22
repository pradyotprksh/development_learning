fn main() {
    variables_and_mutability();

    data_types();

    functions();

    control_flow();

    exercise();

    ownership();

    mutability();
}

fn variables_and_mutability() {
    let x = 5;
    println!("X valus is {x}");
    // x = 12; - Error: By default variables are immutable in rust
    let mut x = 5;
    println!("mutable X valus is {x}");
    x = 12;
    println!("mutable X valus is {x}");

    const SECONDS_IN_FIVE_MINUTES: u32 = 5 * 60;
    println!("{SECONDS_IN_FIVE_MINUTES}");
}

fn data_types() {
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

fn functions() {
    another_function();
    another_function_1(12);
    print_labeled_measurement(12, 'c');
    let x = five();
    println!("The value of x is: {x}");
    let x = plus_one(10);
    println!("The value of x is: {x}");
}

fn another_function() {
    println!("Another function.");
}

fn another_function_1(x: i32) {
    println!("The value of x is: {x}");
}

fn print_labeled_measurement(value: i32, unit_label: char) {
    println!("The measurement is: {value}{unit_label}");
}

fn five() -> i32 {
    5
}

fn plus_one(x: i32) -> i32 {
    x + 1
}
/*
This is wrong, beacuse ; make the line a statement rather than an expression
fn plus_one(x: i32) -> i32 {
    x + 1;
}
*/

fn control_flow() {
    let number = 3;
    if number > 5 {
        println!("Number is greater than 5");
    } else {
        println!("Number is less than or equal to 5");
    }

    let number = 6;
    if number % 4 == 0 {
        println!("number is divisible by 4");
    } else if number % 3 == 0 {
        println!("number is divisible by 3");
    } else if number % 2 == 0 {
        println!("number is divisible by 2");
    } else {
        println!("number is not divisible by 4, 3, or 2");
    }

    let condition = true;
    let number = if condition { 5 } else { 6 };
    println!("The value of number is: {number}");

    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;
        }
    };
    println!("The result is {result}");

    let mut count = 0;
    'counting_up: loop {
        println!("count = {count}");
        let mut remaining = 10;
        loop {
            println!("remaining = {remaining}");
            if remaining == 9 {
                break;
            }
            if count == 2 {
                break 'counting_up;
            }
            remaining -= 1;
        }
        count += 1;
    }
    println!("End count = {count}");

    let mut number = 3;
    while number != 0 {
        println!("{number}!");

        number -= 1;
    }
    println!("LIFTOFF!!!");

    let a = [10, 20, 30, 40, 50];
    let mut index = 0;
    while index < 5 {
        println!("the value is: {}", a[index]);
        index += 1;
    }
    for element in a {
        println!("the value is: {element}");
    }
    for number in (1..4).rev() {
        println!("{number}!");
    }
    println!("LIFTOFF!!!");
}

fn exercise() {
    let cs = get_celsius_fr(1.0, String::from("cs"));
    println!("1 fr => {cs} cs");
    let fr = get_celsius_fr(1.0, String::from("fr"));
    println!("1 cs => {fr} fr");

    let fin_n = n_fibonacci_number(5);
    println!("5th fibonacci {fin_n}");
    let fin_n = n_fibonacci_number(13);
    println!("5th fibonacci {fin_n}");

    tweleve_days_of_christmas();
}

fn get_celsius_fr(temp: f32, to: String) -> f32 {
    if to == "fr" {
        temp * (9.0 / 5.0) + 32.0
    } else {
        (temp - 32.0) / (9.0 / 5.0)
    }
}

fn n_fibonacci_number(n: i32) -> i32 {
    let mut num1 = 0;
    let mut num2 = 1;

    for _n in 1 .. n - 1 {
        let temp = num2;
        num2 = num1 + num2;
        num1 = temp;
    }

    num2
}

fn tweleve_days_of_christmas() {
    let days = ["first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eigth", "ninth", "tenth", "eleventh", "twelve"];
    let gifts = ["A partridge in a pear tree!", "Two turtle doves", "Three french hens", "Four calling birds", "Five golden rings!", "Six geese a layin'", "Seven swans a swimmin'", "Eight maids a milkin'", "Nine ladies dancin'", "Ten lords a leapin'", "Eleven pipers pipin'", "Twelve drummers drummin'"];

    for i in 0 .. 12 {
        let day = days[i];
        println!("On the {day} day of Christmas\nMy true love sent to me");

        for j in (0 .. i + 1).rev() {
            if i > 0 && j == 0 {
                let gift = gifts[j].to_lowercase();
                println!("and {gift}");
            } else {
                let gift = gifts[j];
                println!("{gift}");
            }
        }

        println!("");
    }
}

fn ownership() {
    let nums = vec![2, 4, 6];
    println!("nums {:?}", nums);
    let nums2 = nums;
    println!("nums2 {:?}", nums2);

    // below line will not execute due to ownership transfer
    //
    // ownership logic happens at compile time, not runtime
    // rust will know no other is using it so it can be delocated
    // drop() is the api which drops the memory when not needed.
    // there is no GC kinf of concept in Rust.
    //
    // rule of ownership
    // 1. Each value in Rust has an owner.
    // 2. There can only be one owner at a time.
    // 3. When the owner goes out of scope, the value will be dropped.
    // println!("{:?}", nums);

    // this will work because of shadowing
    let nums = vec![2, 4, 6];
    println!("nums {:?}", nums);
    
    print_nums(nums);
    // this will not work because nums ownership moved to print_nums function
    // this can be fixed by returning the nums back from print_nums
    // but that's kind of a hack and sending the ownership back
    // println!("nums {:?}", nums);

    // for the above issue, we can use refrence concept in Rust.
    // borrower concpet can be used.
    let nums = vec![2, 4, 6];
    let nums_ref: &Vec<i32> = &nums;
    let nums_ref1: &Vec<i32> = &nums;
    let nums_ref2: &Vec<i32> = &nums;
    // the below is not allowed because the borrower is still alive
    // for the owner to die all the borrower should die.
    // let nums2 = nums;
    // And also borowwer can't live past the owner like below.
    // let num_ref: &Vec<i32>;
    // {
    //     let nums = vec![2, 4, 6];
    //     num_ref = &nums;
    // }
    // println!("{:?}", num_ref);
    println!("nums_ref {:?}", nums_ref);
    println!("nums_ref {:?}", nums_ref1);
    println!("nums_ref {:?}", nums_ref2);
    // with the same concept the below will work now since we are passing 
    // the reference not the ownership now
    print_nums_ref(&nums);
    println!("nums {:?}", nums);
}

fn print_nums(nums: Vec<i32>) {
    for num in nums.iter() {
        println!("{}", num);
    }
}

fn print_nums_ref(nums: &Vec<i32>) {
    for num in nums.iter() {
        println!("{}", num);
    }
}

fn mutability() {
    let mut nums = vec![2, 4, 6];
    nums.push(8);

    // the below code will not work because reference by default
    // are immutable, we have to make it mutable by stating it
    // let mut nums_ref = &nums;
    // nums_ref.push(10);

    let mut nums_ref = &mut nums;
    nums_ref.push(10);

    // primitives are 'copy' able but vec is clonable but not copyable
    // if array is holding the primitves then still the copyable concept 
    // will work.
}