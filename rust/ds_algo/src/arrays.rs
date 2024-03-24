pub fn arrays_example() {
    let numbers: [i32; 3] = [1, 2, 3];
    /*
    The println!("{:?}", numbers); statement prints the array numbers using the {:?} format specifier,
    which is useful for debugging purposes as it prints the array with debug formatting.
    */
    println!("{:?}", numbers); // [1, 2, 3]

    let mut my_array = [0; 5];
    println!("{:?}", my_array); // [0, 0, 0, 0, 0]

    my_array[0] = 1;
    my_array[1] = 2;
    my_array[2] = 3;
    my_array[3] = 4;
    my_array[4] = 5;
    println!("{:?}", my_array); // [1, 2, 3, 4, 5]

    my_array[4] = 6;
    println!("{:?}", my_array); // [1, 2, 3, 4, 6]

    // index out of bounds: the length is 5 but the index is 5
    // my_array[5] = 6;

    println!("Length: {}", my_array.len());

    println!("Last element: {}", my_array[my_array.len() - 1]);

    for a in my_array {
        print!("{} ", a);
    }
    println!();

    for i in 0..my_array.len() {
        println!("Index = {} Item = {} ", i, my_array[i]);
    }

    let arr = [3, 2, 4, 7, 10, 6, 5];

    println!("Remove Even Elements from {:?}. Result: {:?}", arr, remove_even(arr)); // [3, 7, 5]
    println!("Reverse {:?}. Result: {:?}", arr, reverse_array(arr)); // [5, 6, 10, 7, 4, 2, 3]
    println!("Minimum element in {:?} is {}", arr, find_min(arr)); // 2
    println!("Second max element in {:?} is {}", arr, second_max(arr)); // 7

    let arr = [8, 1, 0, 2, 1, 0, 3];
    println!("Moving all the zeroes in {:?} in the end. Result: {:?}", arr, move_zeros_to_end(arr)); // [8, 1, 2, 1, 3, 0, 0]

    let arr = [2, 3, 5, 7, 1, 4, 8];
    println!("Missing element in {:?} is {}", arr, find_missing_element(arr)); // 6

    println!("Is madam plaindrome? - {}", is_palindrome("madam"));
    println!("Is Madam plaindrome? - {}", is_palindrome("Madam"));
    println!("Is Rust plaindrome? - {}", is_palindrome("Rust"));
}

/*
In Rust, arrays have a fixed size known at compile time. Therefore, 
returning an array whose size may vary at runtime is not directly possible. 
Instead, you can return a fixed-size array, but this would require specifying 
the maximum possible number of odd elements in advance, which might not 
be practical.

We can do something like
fn find_odd_elements(array: &[i32]) -> [i32; 6]

but that desn't make sense, since then the output would be
[3, 7, 5, 0, 0, 0, 0]
*/
fn remove_even(arr: [i32; 7]) -> Vec<i32> {
    let mut odd_arr = Vec::new();
    for a in arr {
        if a % 2 != 0 {
            odd_arr.push(a);
        }
    }

    odd_arr
}

fn reverse_array(mut arr: [i32; 7]) -> [i32; 7] {
    /*
    Reverse by creating an array.

    let mut rev_arr = [0; 7];

    for i in 0..arr.len() {
        rev_arr[i] = arr[arr.len() - 1 - i];
    }

    return rev_arr;
    */

    let mut start = 0;
    let mut end = arr.len() - 1;

    while start < end {
        let temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start += 1;
        end -= 1;
    }

    arr
}

fn find_min(arr: [i32; 7]) -> i32 {
    let mut min = arr[0];

    for a in arr {
        if a < min {
            min = a;
        }
    }

    min
}

fn second_max(arr: [i32; 7]) -> i32 {
    let mut max = i32::MIN;
    let mut second_max = i32::MIN;

    for a in arr {
        if a > max {
            second_max = max;
            max = a;
        } else if a > second_max {
            second_max = a;
        }
    }

    second_max
}

/*
Understand the code from https://www.youtube.com/watch?v=2ZLl8GAk1X4&t=9663s
*/
fn move_zeros_to_end(mut arr: [i32; 7]) -> [i32; 7] {
    let mut j = 0;

    for i in 0..arr.len() {
        if arr[i] != 0 && arr[j] == 0 {
            let temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        if arr[j] != 0 {
            j += 1;
        }
    }

    arr
}

fn find_missing_element(arr: [i32; 7]) -> i32 {
    let mut element_sum = 0;
    for a in arr {
        element_sum += a;
    }

    let n = arr.len() + 1;
    let num_sum = (n * (n + 1) / 2) as i32;

    num_sum - element_sum
}

/*
To check if a string is a palindrome in Rust, you can compare characters 
from both ends of the string and check if they match.
*/
fn is_palindrome(value: &str) -> bool {
    let s = value.trim().to_lowercase();
    let chars: Vec<char> = s.chars().collect();

    let mut start = 0;
    let mut end = chars.len() - 1;

    while start <= end {
        if chars[start] != chars[end] {
            return false;
        }
        start += 1;
        end -= 1;
    }

    true
}