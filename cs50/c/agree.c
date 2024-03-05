#include<stdio.h>

int main(void) {
    char c = 'Y';

    if (c == 'y' || c == 'Y') {
        printf("Agreed!!\n");
    } else if (c == 'n' || c == 'N') {
        printf("Not Agreed!!\n");
    }
}