#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

int main() {
    int fd[2];

    if (pipe(fd) < 0) {
        exit(1);
    }

    int rc1 = fork();

    if (rc1 < 0) {
        fprintf(stderr, "Fork 1 failed\n");
        exit(1);
    } 
    else if (rc1 == 0) {
        printf("-> Child process 1\n");
        close(fd[0]);
        dup2(fd[1], 1);
        printf("-> Child piped\n");
    } 
    else {
        int rc2 = fork();

        if (rc2 < 0) {
          fprintf(stderr, "Fork 2 failed\n");
            exit(1);
        } 
        else if (rc2 == 0) {
          printf("-> Child process 2\n");
          close(fd[1]);
          dup2(fd[0], 0);

          char buff[512];
          read(STDIN_FILENO, buff, 512);
          printf("%s", buff);

        } 
        else {
          waitpid(rc2, NULL, 0);
          printf("goodbye\n");
        }
    }

    return 0;
}