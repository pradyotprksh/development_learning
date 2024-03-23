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