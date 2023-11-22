#ifndef FILEINFO_H
#define FILEINFO_H

#include <limits.h>
#include <stdio.h>
#include <dirent.h>
#include <errno.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <string.h>
#include<unistd.h>

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
        int bytes;
        fileinfo* list;
    };
};

fileinfo* fileinfo_create(char* name);
void fileinfo_print(fileinfo* info);
void fileinfo_destroy(fileinfo* info);

extern fileinfo* list_directory(char* directory);
extern void print_regular(char const* name, int bitlength);
extern void print_directory(char const* path, char const* filename, fileinfo* files);
extern void print_other(char const* name);

#endif