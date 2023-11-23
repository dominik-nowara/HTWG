#ifndef FILEINFO_H
#define FILEINFO_H

#include <limits.h>
#include <stdlib.h>

typedef struct fileinfo fileinfo;

enum filetype {
    filetype_regular,
    filetype_directory,
    filetype_other
};

struct fileinfo {
    char filename[NAME_MAX];
    enum filetype type;
    fileinfo* next;

    union  {
        size_t bytes;
        fileinfo* list;
    };
};

fileinfo* fileinfo_create(char* name);
void fileinfo_print(fileinfo* info);
void fileinfo_destroy(fileinfo* info);

#endif