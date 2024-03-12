#include <stdio.h>

int main (void) {
    int zahl_i = 10;
    short zahl_s = 12;
    long zahl_l = 123L;
    unsigned zahl_u = 0xffffffffU;
    unsigned short zahl_us = 0xffffU;
    unsigned long zahl_ul = 0xffffffffffffUL;
    float zahl_float = 3.14F;
    double zahl_double = 3.14;
    long double zahl_ld = 3.14L;
    char character = 'b';
    signed char s_char = -5;
    unsigned char u_char = 0xDE;
    const char* string = "Hal";
    
    printf("Adresse\t\tPlatzbedarf\tTyp\t\t\tName\t\tWert\n");
    printf("%p\t%lu\t\t%s\t\t\t%s\t\t%d\n", (void*) &zahl_i, (unsigned long) sizeof zahl_i, "Int", "zahl_i", zahl_i);
    printf("%p\t%lu\t\t%s\t\t%s\t\t%hd\n", (void*) &zahl_s, (unsigned long) sizeof zahl_s, "Short Int", "zahl_s", zahl_s);
    printf("%p\t%lu\t\t%s\t\t%s\t\t%ld\n", (void*) &zahl_l, (unsigned long) sizeof zahl_l, "Long Int", "zahl_l", zahl_l);
    printf("%p\t%lu\t\t%s\t\t%s\t\t%o\n", (void*) &zahl_u, (unsigned long) sizeof zahl_u, "Unsigned Int", "zahl_u", zahl_u);
    printf("%p\t%lu\t\t%s\t%s\t\t%hu\n", (void*) &zahl_us, (unsigned long) sizeof zahl_us, "Unsigned Short Int", "zahl_us", zahl_us);
    printf("%p\t%lu\t\t%s\t%s\t\t%lu\n", (void*) &zahl_ul, (unsigned long) sizeof zahl_ul, "Unsigned Long Int", "zahl_ul", zahl_ul);
    printf("%p\t%lu\t\t%s\t\t\t%s\t%f\n", (void*) &zahl_float, (unsigned long) sizeof zahl_float, "Float", "zahl_float", zahl_float);
    printf("%p\t%lu\t\t%s\t\t\t%s\t%lf\n", (void*) &zahl_double, (unsigned long) sizeof zahl_double, "Double", "zahl_double", zahl_double);
    printf("%p\t%lu\t\t%s\t\t%s\t\t%Lf\n", (void*) &zahl_ld, (unsigned long) sizeof zahl_ld, "Long Double", "zahl_ld", zahl_ld);
    printf("%p\t%lu\t\t%s\t\t\t%s\t%c\n", (void*) &character, (unsigned long) sizeof character, "Char", "character", character);
    printf("%p\t%lu\t\t%s\t\t%s\t\t%hhd\n", (void*) &s_char, (unsigned long) sizeof s_char, "Signed Char", "s_char", s_char);
    printf("%p\t%lu\t\t%s\t\t%s\t\t%hhu\n", (void*) &u_char, (unsigned long) sizeof u_char, "Unsigned Char", "u_char", u_char);
    printf("%p\t%lu\t\t%s\t\t\t%s\t\t%s\n", (void*) &string, (unsigned long) sizeof string, "Char*", "string", string);

    if (sizeof(int) == 4 && sizeof(long) == 4 && sizeof(char*) == 4)
        printf("\nILP32\n");
    else if (sizeof(int) == 4 && sizeof(long) == 8 && sizeof(char*) == 8)
        printf("\nLP64\n");
    else if (sizeof(int) == 4 && sizeof(long) == 4 && sizeof(long long) == 8 && sizeof(char*) == 8)
        printf("\nLLP64\n");
    else
        printf("\nEs handelt sich um keins der drei Modelle\n");

    return 0;
}