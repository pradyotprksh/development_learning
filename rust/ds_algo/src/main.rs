mod arrays;
mod notes;
mod singly_linked_list;
mod time_complexity;

fn main() {
    notes::ds_intro();

    notes::algo_intro();
    
    notes::algo_analysis();

    notes::time_complexity(time_complexity::time_complexity_example);
    notes::space_complexity();

    notes::asymptotic_analysis_of_algorithm();

    notes::arrays(arrays::arrays_example);

    notes::singly_linked_list(singly_linked_list::singly_linked_list_example)
}
