#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    int x = 100;
    int rc = fork();

    // rc == 0: print child
    // rc > 0: print parent
    // rc < 0: Fork failed

    if (rc == 0) {
        x = 200;
        printf("Child process - x: %d\n", x);
    } 
    else if (rc > 0) {
        x = 300;
        printf("Parent process - x: %d\n", x);
    }
    else {
        fprintf(stderr, "Forking failed\n");
        exit(1);
    }

    return 0;
}