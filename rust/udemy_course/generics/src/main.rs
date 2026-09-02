use num_traits::{Float, ToPrimitive};

// fn solve(a: f64, b: f64) -> f64 {
//     (a.powi(2) + b.powi(2)).sqrt()
// }

fn solve<T: Float>(a: T, b: T) -> f64 {
    let a_f64 = a.to_f64().unwrap();
    let b_f64 = b.to_f64().unwrap();

    (a_f64.powi(2) + b_f64.powi(2)).sqrt()
}

fn solve_new<T: ToPrimitive, U: ToPrimitive>(a: T, b: U) -> f64 {
    let a_f64 = a.to_f64().unwrap();
    let b_f64 = b.to_f64().unwrap();

    (a_f64.powi(2) + b_f64.powi(2)).sqrt()
}

fn main() {
    let a = 3.0;
    let b = 3.0;

    println!("{}", solve(a, b));

    let c: u8 = 5;
    let d = 5.6;

    println!("{}", solve_new(c, d));
}
