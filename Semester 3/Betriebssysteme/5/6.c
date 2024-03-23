#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int main() {
    int rc = fork();
    
    if (rc == 0) {
        printf("-> Child process. PID: %d\n", getpid());
        exit(0);
    }
    else if (rc > 0) {
        int status;
        waitpid(rc, &status, 0);
        printf("-> Parent process. PID: %d\n", getpid());        
    }
    else {
        fprintf(stderr, "Forking failed\n");
        exit(1);
    }

    return 0;
}