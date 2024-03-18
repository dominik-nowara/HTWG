#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void bubblesort(char** a, int length) {
    for (int i = length; i > 1; --i) {
        // groessten Wert nach hinten schieben
        for (int j = 0; j < i - 1; ++j) {
            if (strcmp(a[j], a[j + 1]) > 0) {
                // Werte tauschen
                char* tmp = a[j];
                a[j] = a[j+1];
                a[j+1] = tmp;
            }
        }
    }
}

int main(int argc, char *argv[]) {
    //--------------------------------------------- Arraygroesse bestimmen
    if (argc != 2) {
        printf("Aufruf: java Stringsort Anzahl");
        return 1;
    }

    int n = atoi(argv[1]);
    if (n < 1) {
        printf("Anzahl muss midestens 1 sein");
        return 1;
    }

    char** a = malloc(n * sizeof(char*));

    if (a == NULL) {
        return 12;
    }

    srand((unsigned int) time(NULL));
    int returnSize = 0;

    printf("Unsortiertes Array:\n");
    for (int i = 0; i < n; ++i) {
        int r = rand() % (n + 1);
        a[i] = malloc(sizeof(argv[1]) + 1);
        if (a[i] == NULL) {
            return 12;
        }
        returnSize += sprintf(a[i], "%d", r);
        printf("%s ", a[i]);
    }

    printf("\n");

    //-------------------------------------------------- Strings sortieren
    bubblesort(a, n);

    //--------------------------------------------------- Strings ausgeben
    printf("Sortiertes Array:\n");

    returnSize *= 2;
    char* returnString = (char*) malloc(returnSize * sizeof(char) + 1);

    if (returnString == NULL) {
        return 12;
    }

    strcpy(returnString, a[0]);
    for (int i = 1; i < n; ++i) {
        if (strcmp(a[i], a[i - 1]) == 0) {
            strcat(returnString, "*");
        } 
        else {
            strcat(returnString, " ");
            strcat(returnString, a[i]);
        }
    }

    printf("%s\n", returnString);

    for (int i = 0; i < n; ++i) {
        free(a[i]);
    }

    free(returnString);
    free(a);

    return 0;
}