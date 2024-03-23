#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int rc = fork();

    if (rc == 0) {
        close(STDOUT_FILENO);
        printf("-> Child process - does this show up?\n");
    } 
    else if (rc > 0) {
        wait(NULL);
        printf("-> Parent process\n");
    }
    else {
        fprintf(stderr, "Forking failed\n");
        exit(1);
    }
}