#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char *argv[]) {
    srand((unsigned int) time(NULL));

    //---------------------------------------------- Arraygroesse einlesen
    if (argc != 2) {
        printf("Array muss eine Zahl übergeben werden!");
        return 1;
    }

    int n = atoi(argv[1]);
    int *a = (int*)malloc(n * sizeof(int));

    if (a == NULL) {
        return 12;
    }

    //---------------------------------------------------- Zahlen einlesen
    printf("Bitte %d ganze Zahl eingeben: ", n);
    int k = 0;
    for (int i = 0; i < n; ++i) {
        if (scanf("%d", &a[i]) == 1) {
            ++k;
        }
    }

    for (int i = k; i < n; ++i) {
        a[i] = rand();
        printf("%d\n", a[i]);
    }

    //--------------------------------------------------- Zahlen sortieren
    for (int i = n; i > 1; --i) {
        // groessten Wert nach hinten schieben
        for (int j = 0; j < i - 1; ++j) {
            if (a[j] > a[j + 1]) {
                // Werte tauschen
                int tmp = a[j + 1];
                a[j + 1] = a[j];
                a[j] = tmp;
            }
        }
    }

    //---------------------------------------------------- Zahlen ausgeben
    printf("Sortierte Zahlenfolge: \n");
    for (int m = 0; m < n; m++)
    {
        printf("%d\n", a[m]);
    }

    free(a);
    return 0;
}