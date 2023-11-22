/*
 * listfiles.c
 *
 * Listet Dateien auf.
 *
 * Author H.Drachenfels
 * Erstellt am: 14.9.2023
 */
#define _POSIX_C_SOURCE 200112L

#include "fileinfo.h"

#include <stdio.h>
#include <errno.h>  // errno
#include <string.h> // strerror

int main(int argc, char *argv[])
{
    fileinfo *files = NULL;
    fileinfo *dirs = NULL;
    for (int i = argc - 1; i > 0; --i)
    {
        fileinfo *f = fileinfo_create(argv[i]);
        if (!f)
        {
            fprintf(stderr, "%s: %s (errno %d)\n",
                    argv[i], strerror(errno), errno);
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

    fileinfo *head;
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

    for (fileinfo *f = head; f; f = f->next)
    {
        fileinfo_print(f);
    }

    while (head)
    {
        fileinfo *next = head->next;
        fileinfo_destroy(head);
        head = next;
    }
}

