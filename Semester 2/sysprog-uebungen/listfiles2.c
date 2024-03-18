#define _POSIX_C_SOURCE 1
#include <asm-generic/errno.h>
#include <errno.h>
#include <limits.h>
#include <linux/limits.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>
#include <stdlib.h>

typedef struct fileinfo fileinfo;

enum filetype {
    filetype_regular,
    filetype_directory,
    filetype_other
};
 
struct fileinfo {
    char filename[NAME_MAX];
    enum filetype filetype;
    fileinfo* list;
    
    union  {
        int byteLength;
        fileinfo* files;
    };
};

fileinfo* fileinfo_create(char* name) {
    if (strlen(name) > NAME_MAX) {
        errno = ENAMETOOLONG;
        return NULL;
    }

    
}

void fileinfo_print(fileinfo* info) {

}

void fileinfo_destroy(fileinfo* info) {
    if (info->filetype == filetype_directory) {
        
        return;
    }
    
}


int main(int argc, char *argv[]) {
    struct stat sb;

    if (argc < 2) {
        fprintf(stderr, "Usage: %s <pathname>\n", argv[0]);
        return 1;
    }

    if (stat(argv[1], &sb) == -1) {
        perror("stat");
        return 1;
    }

    if (S_ISDIR(sb.st_mode)) {
        printf("DIR");
    }
    else if (S_ISREG(sb.st_mode)) {
        printf("REGULAR FILE");
    }
    else {
        printf("ELSE");
    }

    return 0;
}
