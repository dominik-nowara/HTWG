#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>

int main() {
    int backend_file = open("file3.txt", O_CREAT | O_RDWR | O_TRUNC, S_IRWXU);
    int rc = fork();

    if (rc == 0)
    {
        write(backend_file, "true", 4);
        printf("-> Child process\n");
        printf("hello\n");
        close(backend_file);
    }
    else if (rc > 0)
    {
        char buffer[4];
        buffer[4] = '\0';
        while (strcmp(buffer, "true\0") != 0) {
            close(backend_file);
            backend_file = open("file3.txt", O_CREAT | O_RDWR, S_IRWXU);
            read(backend_file, buffer, 4);
            usleep(10000);
        }

        printf("-> Parent process\n");
        printf("goodbye\n");
    }
    else {
        fprintf(stderr, "Forking failed\n");
        exit(1);
    }

    close(backend_file);
    return 0;
}