#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int rc = fork();

    if (rc == 0) {
        int wc = wait(NULL);
        printf("-> Child process\n");
        printf("Wait return code: %d\n", wc);
    }
    else if (rc > 0) {
        int wc = wait(NULL);
        printf("-> Parent process\n");
        printf("Wait return code: %d\n", wc);
    }
    else {
        fprintf(stderr, "Forking failed\n");
        exit(1);
    }

    return 0;
}