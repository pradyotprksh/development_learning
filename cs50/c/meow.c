// #include<stdbool.h>
#include<stdio.h>

int main(void) {
    int count = 0;
    while (count < 3) {
        printf("Meow - While\n");
        ++count;
    }
    
    for (int i = 0; i < 3; i++) {
        printf("Meow - For\n");
    }

    // // while(true) {
    //     printf("Meow - infinite\n");
    // }
}