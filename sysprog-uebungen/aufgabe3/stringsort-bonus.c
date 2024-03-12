#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


void bubblesort(char* a, int length, int m) {
    for (int i = length; i > 1; --i) {
        // groessten Wert nach hinten schieben
        for (int j = 0; j < i - 1; ++j) {
            if (strcmp(a + j * m, a + j * m + 1) > 0) {
                // Werte tauschen
                char* tmp = a + j * m;
                (a + j * m) = a + j * m + 1;
                a[j * m + 1] = tmp;
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

    int m = strlen(argv[1]) + 1;
    
    char* a = (char*) malloc(n * m * sizeof (char));

    if (a == NULL) {
        return 12;
    }

    srand((unsigned int) time(NULL));

    printf("Unsortiertes Array:\n");
    for (int i = 0; i < n; ++i) {
        int r = rand() % n;
        sprintf(a + i * m, "%d", r);
        printf("%s ", a + i * m);
    }
    printf("%s ", a);

    printf("\n");

    //-------------------------------------------------- Strings sortieren
    bubblesort(a, n, m);

    //--------------------------------------------------- Strings ausgeben
    printf("\nSortiertes Feld:\n");
    char *returnString = (char*) malloc(n * m * sizeof(char));
    if (returnString == NULL)
    {
        return 12;
    }
    strcpy(returnString, a);
    for (int i = 1; i < n; ++i)
    {
        if (strcmp(a + i * m, a + (i - 1) * m) == 0)
        {
            strcat(returnString, "*");
        }
        else
        {
            strcat(returnString, " ");
            strcat(returnString, a + i * m);
        }
    }

    printf("%s\n", returnString);

    free(returnString);
    free(a);
    return 0;
}