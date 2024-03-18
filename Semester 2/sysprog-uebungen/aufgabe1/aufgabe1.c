#include <stdio.h>
#include <stdbool.h>

int main (void) {
    int zahl_i = 10;
    short zahl_s = 12;
    long int zahl_l = 123L;
    long long int zahl_ll = 1234LL;
    unsigned int zahl_u = 0xffffffffU;
    unsigned short int zahl_us = 0xffffU;
    unsigned long int zahl_ul = 0xffffffffffffUL;
    unsigned long long int zahl_ull = 0xffffffffffffffffULL;
    float zahl_float = 3.14F;
    double zahl_double = 3.14;
    long double zahl_ld = 3.14L;
    char character = 'b';
    signed char s_char = -5;
    unsigned char u_char = 0xDE;
    bool boolean = false;
    const char* s = "Hallo";
    
    printf("Adresse\t\tPlatzbedarf\tTyp\t\t\tName\t\tWert\n");
    printf("%p\t%zu\t\t%s\t\t\t%s\t%d\n", (void*) &zahl_i, sizeof zahl_i, "Int", "zahl_i", zahl_i);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%hd\n", (void*) &zahl_s, sizeof zahl_s, "Short Int", "zahl_s", zahl_s);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%ld\n", (void*) &zahl_l, sizeof zahl_l, "Long Int", "zahl_l", zahl_l);
    printf("%p\t%zu\t\t%s\t\t%s\t%lld\n", (void*) &zahl_ll, sizeof zahl_ll, "Long Long Int", "zahl_ll", zahl_ll);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%o\n", (void*) &zahl_u, sizeof zahl_u, "Unsigned Int", "zahl_u", zahl_u);
    printf("%p\t%zu\t\t%s\t%s\t%hu\n", (void*) &zahl_us, sizeof zahl_us, "Unsigned Short Int", "zahl_us", zahl_us);
    printf("%p\t%zu\t\t%s\t%s\t%lu\n", (void*) &zahl_ul, sizeof zahl_ul, "Unsigned Long Int", "zahl_ul", zahl_ul);
    printf("%p\t%zu\t\t%s\t%s\t%llu\n", (void*) &zahl_ull, sizeof zahl_ull, "Unsigned Long Long Int", "zahl_ull", zahl_ull);
    printf("%p\t%zu\t\t%s\t\t\t%s\t%f\n", (void*) &zahl_float, sizeof zahl_float, "Float", "", zahl_float);
    printf("%p\t%zu\t\t%s\t\t\t%s\t%lf\n", (void*) &zahl_double, sizeof zahl_double, "Double", "", zahl_double);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%Lf\n", (void*) &zahl_ld, sizeof zahl_ld, "Long Double", "", zahl_ld);
    printf("%p\t%zu\t\t%s\t\t\t%s\t%c\n", (void*) &character, sizeof character, "Char", "", character);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%hhd\n", (void*) &s_char, sizeof s_char, "Signed Char", "", s_char);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%hhu\n", (void*) &u_char, sizeof u_char, "Unsigned Char", "", u_char);
    printf("%p\t%zu\t\t%s\t\t\t%s\t\t%d\n", (void*) &boolean, sizeof boolean, "Boolean", "", boolean);
    printf("%p\t%zu\t\t%s\t\t\t%s\t\t%s\n", (void*) &s, sizeof s, "Char*", "", s);

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