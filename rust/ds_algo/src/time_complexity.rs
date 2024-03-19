use chrono::Utc;

pub fn time_complexity_example() {
    let num: i64 = get_current_timestamp();
    
    let start_time = get_current_timestamp();
    let sum_by_formula = find_sum_by_formula(num);
    let end_time = get_current_timestamp();
    println!(" Find sum of {} number by formula: {}. Took {} milliseconds", num, sum_by_formula, end_time - start_time);

    let start_time = get_current_timestamp();
    let sum_by_iteration = find_sum_by_iteration(num);
    let end_time = get_current_timestamp();
    println!(" Find sum of {} number by iteration: {}. Took {} milliseconds", num, sum_by_iteration, end_time - start_time);
}

fn find_sum_by_formula(n: i64) -> i64 {
    return n * (n + 1) / 2;
}

fn find_sum_by_iteration(n: i64) -> i64 {
    let mut sum: i64 = 0;

    for num in 1..=n {
        sum += num;
    }

    return sum;
}

fn get_current_timestamp() -> i64 {
    let current_time = Utc::now();
    let timestamp = current_time.timestamp();
    return timestamp;
}