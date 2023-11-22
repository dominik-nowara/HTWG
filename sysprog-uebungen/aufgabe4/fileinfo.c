#include <asm-generic/errno-base.h>
#define _POSIX_C_SOURCE 200112L

#include "fileinfo.h"

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>  // errno
#include <string.h> // strerror
#include <sys/stat.h>

fileinfo* fileinfo_create(char* name) {
    int nameLength = strlen(name);
    if (nameLength > NAME_MAX) {
        errno = ENAMETOOLONG;
        return NULL;
    }

    struct stat sb;

    if (lstat(name, &sb) == -1) {
        errno = ENOENT;
        return NULL;
    }

    fileinfo* info = malloc(sizeof(fileinfo));
    strcpy(info->filename, name);

    if (S_ISDIR(sb.st_mode)) {
        info->type = filetype_directory;
        info->list = list_directory(name);
    }
    else if (S_ISREG(sb.st_mode)) {
        info->type = filetype_regular;

        FILE *fp = fopen(name, "r");
        if (fp == NULL) {
            errno = ENOENT;
            free(info);
            return NULL;
        }

        unsigned bytes = 0;
        while (fgetc(fp) != EOF) {
            ++bytes;
        }

        if (ferror(fp)) {
            errno = EIO;
            fclose(fp);
            return NULL;
        }

        info->bytes = bytes;
        fclose(fp);
    }
    else {
        info->type = filetype_other;
    }

    return info;
}

void fileinfo_print(fileinfo* info) {
    if (info->type == filetype_directory) {
        print_directory("", info->filename, info->list);
    }
    else if (info->type == filetype_regular) {
        print_regular(info->filename, info->bytes);
    }
    else {
        print_other(info->filename);
    }
}

void fileinfo_destroy(fileinfo* info) {
    if (info->type == filetype_directory) {
        fileinfo* current = info->list;

        while (current)
        {
            fileinfo *next = current->next;

            if (current->type == filetype_directory) {
                fileinfo_destroy(current->list);
            }

            free(current);
            current = next;
        }
    }

    free(info);
}

fileinfo* list_directory(char* directory) {
    DIR *d = opendir(directory);
    if (d == NULL) {
        errno = ENOENT;
        return NULL;
    }

    fileinfo *files = NULL;
    fileinfo *dirs = NULL;

    chdir(directory);

    struct dirent *e;
    while ((e = readdir(d)) != NULL) {
        if (strcmp(e->d_name, ".") == 0
                || strcmp(e->d_name, "..") == 0) {
            continue;
        }

        fileinfo *f = fileinfo_create(e->d_name);
        if (!f) {
            continue;
        }

        if (f->type == filetype_directory)
        {
            f->next = dirs;
            dirs = f;
        }
        else
        {
            f->next = files;
            files = f;
        }
    }

    fileinfo* head;
    if (!files)
    {
        head = dirs;
    }
    else
    {
        head = files;

        while (files->next)
        {
            files = files->next;
        }

        files->next = dirs;
    }

    closedir(d);
    chdir("..");

    return head;
}

void print_regular(char const* name, int bitlength) {
    printf("%s (regular, %d Byte)\n", name, bitlength);
}

void print_directory(char const* path, char const* filename, fileinfo* files) {
    if (strcmp(path, "") != 0) {
        printf("\n%s/%s:\n", path, filename);
    }
    else {
        printf("\n%s:\n", filename);
    }

    if (files != NULL) {
        if (files->type == filetype_directory) {
            printf("%s (directory)\n", files->filename);
            print_directory(filename, files->filename, files->list);
        }
        else {
            fileinfo_print(files);
        }

        while (files->next != NULL) {
            if (files->next->type == filetype_directory) {
                printf("%s (directory)\n", files->filename);
                print_directory(filename, files->filename, files->list);
            }
            else {
                fileinfo_print(files->next);
            }
        }
    }
}

void print_other(char const* name) {
    printf("%s (other)\n", name);
}
