mod notes;
mod time_complexity;

fn main() {
    notes::ds_intro();

    notes::algo_intro();
    
    notes::algo_analysis();

    notes::time_complexity(time_complexity::time_complexity_example);
    notes::space_complexity();

    notes::asymptotic_analysis_of_algorithm();
}
