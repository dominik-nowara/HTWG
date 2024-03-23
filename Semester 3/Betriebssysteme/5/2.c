#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>
#include <stdlib.h>

int main() {
    struct stat sb;
    int my_file = open("file2.txt", O_CREAT | O_RDWR, S_IRWXU);
    
    int rc = fork();

    if (rc == 0)
    {
        const char* child_msg = "Child did write here\n";
        printf("-> Child writing into file\n");
        write(my_file, child_msg , strlen(child_msg));
        exit(0);
    }
    else if (rc > 0)
    {
        wait(NULL);
        const char * parent_msg = "Parent did write here\n";
        printf("-> Parent writing into file\n");
        write(my_file, parent_msg, strlen(parent_msg));
    }
    else {
        fprintf(stderr, "! Forking failed !\n");
        exit(1);
    }

    fstat(my_file, &sb);
    lseek(my_file, 0, SEEK_SET);
    char * buffer = malloc(sb.st_size);
    read(my_file, buffer, sb.st_size);

    printf("Content of file: \n%s", buffer);
    close(my_file);

    return 0;
}