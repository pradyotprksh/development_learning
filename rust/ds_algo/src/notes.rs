pub fn ds_intro() {
    println!();
    println!("=*=*=*= DATA STRUCTURES =*=*=*=");
    println!("- Way to organize data.");
    println!("-- Helps in processing the data.");
    println!();
    println!("Two kinds of data structures:");
    println!("- Linear");
    println!("-- Data elements arragned in sequential manner. Each member is connected to previous and next element. Makes easier to traverse them. Eg. Array, Linked List, Stack, Queue, etc.");
    println!("- Non-Linear");
    println!("-- Data elements arragned in non-sequential manner. Each member is connected to previous and next element through paths. Traveral is bit difficult and not easy to implement. Eg. Tree, Graph, etc.");
}

pub fn algo_intro() {
    println!();
    println!("=*=*=*= ALGORITHMS =*=*=*=");
    println!("- Set of instructions to perform a task or solve a problem.");
}

pub fn algo_analysis() {
    println!();
    println!("Algorithm analysis helps in finding the best algorithm which runs fast and takes less memory.");
    println!("Time and space complexity are the two factors which determine the performance of an algorithms.");
}

pub fn time_complexity(example: fn()) {
    println!();
    println!(" Time Complexity");
    println!(" - The amount of time take by an algorithm to run.");
    println!(" Let's see an example below for finding the sum of n numbers using 2 approches");
    example();
    println!(" This is not the correct way of finding the time complexity. Because depending on the machine the time calculation might change and can't give the correct result.");
}

pub fn space_complexity() {
    println!();
    println!(" Space Complexity");
    println!(" - The amount of space or memory taken by an algorithm to run.");
}

pub fn asymptotic_analysis_of_algorithm() {
    println!();
    println!("We usually we don't go by exact number of time and space. So we use a modal to show how much time and space the algorithm is taking.");
    println!("Asymptotic analysis is one of the way to show how much time and space the algorithm is taking. Also based on the input size as well. As we increase the size the time and space will get affected.");
    println!();
    println!("Asymptotic notations helps in detemining the runing time of an algorithm based on input size in -");
    println!("1. Best case");
    println!("2. Average case");
    println!("3. Worst case");
    println!("scenarios");
    println!();
    println!("Different notation for runtime analysis");
    println!("Omega notation - best amount of time an algorithm to complete the task. Mainly the lower bound, best case for an algorithm.");
    println!("Big O notation - Upper bound of an algorithm. Longest amount of time an algorithm will take, worst case for an algorithm.");
    println!("Theta notation - middle bound of an algorithm, average case mainly.");
    println!();
    println!("Big O notiation is the most used notation. Since its shows the longest amount of time for an algorithm for a given input.");
    big_o_notiation();
}

fn big_o_notiation() {
    println!();
    println!("Rules for Big O Notation");
    println!("- Single processor");
    println!("- Sequential execution of statements");
    println!("- Assignment operation takes 1 unit of time");
    println!("- Return statement takes 1 unit of time");
    println!("- Arithmatic operation takes 1 unit of time");
    println!("- Logical operation takes 1 unit of time");
    println!("- Other small/single operation takes 1 unit of time");
    println!("- Drop lower order terms");
    println!(" - Eg. T = n^2 + 3n + 1 ==> O(n^2)");
    println!("- Drop constant multipliers");
    println!(" - Eg. T = 3n^2 + 6n + 1 ==> O(n^2)");
}