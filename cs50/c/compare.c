#include <stdio.h>

int main(void) {
    int x = 20;
    int y = 20;

    if (x < y) {
        printf("x is less than y\n");
    } else if (x > y) {
        printf("x is greater than y\n");
    } else {
        printf("x is equal to y\n");
    }
}
