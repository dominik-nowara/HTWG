/*
 * einstieg.c
 *
 * Das erste C-Programm gibt einen Text aus.
 * 
 * Autor: Dominik Nowara
 * Erstellt am: 12.10.2023
 */

#include <stdio.h>
#include <stdlib.h>

int main (void) {
    printf("Anzahl bisher geschriebener C-Programme eingeben: ");
    int anzahl;
    scanf("%d", &anzahl);

    printf("Vorname angeben: ");
    char *vorname = (char*) malloc(8);
    scanf("%s", vorname);

    printf("%ss %d. C-Programm funktioniert!\n", vorname, anzahl + 1);

    free(vorname);
    return 0;
}