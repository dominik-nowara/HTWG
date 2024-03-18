#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>

int main (void) {
    int8_t i8 = INT8_MAX;
    int16_t i16 = INT16_MAX;
    int32_t i32 = INT32_MAX;
    int64_t i64 = INT64_MAX;

    int_fast8_t if8 = INT_FAST8_MAX;
    int_fast16_t if16 = INT_FAST16_MAX;
    int_fast32_t if32 = INT_FAST32_MAX;
    int_fast64_t if64 = INT_FAST64_MAX;

    int_least8_t il8 = INT_LEAST8_MAX;
    int_least16_t il16 = INT_LEAST16_MAX;
    int_least32_t il32 = INT_LEAST32_MAX;
    int_least64_t il64 = INT_LEAST64_MAX;

    intmax_t im = INTMAX_MAX;
    intptr_t ip = INTPTR_MAX;

    uint8_t ui8 = UINT8_MAX;
    uint16_t ui16 = UINT16_MAX;
    uint32_t ui32 = UINT32_MAX;
    uint64_t ui64 = UINT64_MAX;

    uint_fast8_t uif8 = UINT_FAST8_MAX;
    uint_fast16_t uif16 = UINT_FAST16_MAX;
    uint_fast32_t uif32 = UINT_FAST32_MAX;
    uint_fast64_t uif64 = UINT_FAST64_MAX;

    uint_least8_t uil8 = UINT_LEAST8_MAX;
    uint_least16_t uil16 = UINT_LEAST16_MAX;
    uint_least32_t uil32 = UINT_LEAST32_MAX;
    uint_least64_t uil64 = UINT_LEAST64_MAX;

    uintmax_t uim = UINTMAX_MAX;
    uintptr_t uip = UINTPTR_MAX;

    printf("Adresse\t\tPlatzbedarf\tTyp\t\t\tName\t\tWert\n");
    printf("%p\t%zu\t\t%s\t\t\t%s\t\t%" PRId8 "\n", (void*)&i8, sizeof(i8   ), "int8_t", "i8   ", i8   );
    printf("%p\t%zu\t\t%s\t\t\t%s\t\t%" PRId16 "\n", (void*)&i16, sizeof(i16  ), "int16_t", "i16  ", i16  );
    printf("%p\t%zu\t\t%s\t\t\t%s\t\t%" PRId32 "\n", (void*)&i32, sizeof(i32  ), "int32_t", "i32  ", i32  );
    printf("%p\t%zu\t\t%s\t\t\t%s\t\t%" PRId64 "\n", (void*)&i64, sizeof(i64  ), "int64_t", "i64  ", i64  );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdFAST8 "\n", (void*)&if8, sizeof(if8  ), "int_fast8_t", "if8  ", if8  );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdFAST16 "\n", (void*)&if16, sizeof(if16 ), "int_fast16_t", "if16 ", if16 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdFAST32 "\n", (void*)&if32, sizeof(if32 ), "int_fast32_t", "if32 ", if32 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdFAST64 "\n", (void*)&if64, sizeof(if64 ), "int_fast64_t", "if64 ", if64 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdLEAST8 "\n", (void*)&il8, sizeof(il8  ), "int_least8_t", "il8  ", il8  );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdLEAST16 "\n", (void*)&il16, sizeof(il16 ), "int_least16_t", "il16 ", il16 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdLEAST32 "\n", (void*)&il32, sizeof(il32 ), "int_least32_t", "il32 ", il32 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdLEAST64 "\n", (void*)&il64, sizeof(il64 ), "int_least64_t", "il64 ", il64 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdMAX "\n", (void*)&im, sizeof(im   ), "intmax_t", "im   ", im   );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIdPTR "\n", (void*)&ip, sizeof(ip   ), "intptr_t", "ip   ", ip   );
    printf("%p\t%zu\t\t%s\t\t\t%s\t\t%" PRIu8 "\n", (void*)&ui8, sizeof(ui8  ), "uint8_t", "ui8  ", ui8  );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIu16 "\n", (void*)&ui16, sizeof(ui16 ), "uint16_t", "ui16 ", ui16 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIu32 "\n", (void*)&ui32, sizeof(ui32 ), "uint32_t", "ui32 ", ui32 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIu64 "\n", (void*)&ui64, sizeof(ui64 ), "uint64_t", "ui64 ", ui64 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuFAST8 "\n", (void*)&uif8, sizeof(uif8 ), "uint_fast8_t", "uif8 ", uif8 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuFAST16 "\n", (void*)&uif16, sizeof(uif16), "uint_fast16_t", "uif16", uif16);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuFAST32 "\n", (void*)&uif32, sizeof(uif32), "uint_fast32_t", "uif32", uif32);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuFAST64 "\n", (void*)&uif64, sizeof(uif64), "uint_fst64_t", "uif64", uif64);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuLEAST8 "\n", (void*)&uil8, sizeof(uil8 ), "uint_least8_t", "uil8 ", uil8 );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuLEAST16 "\n", (void*)&uil16, sizeof(uil16), "uint_least16_t", "uil16", uil16);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuLEAST32 "\n", (void*)&uil32, sizeof(uil32), "uint_least32_t", "uil32", uil32);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuLEAST64 "\n", (void*)&uil64, sizeof(uil64), "uint_least64_t", "uil64", uil64);
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuMAX "\n", (void*)&uim, sizeof(uim  ), "uintmax_t", "uim  ", uim  );
    printf("%p\t%zu\t\t%s\t\t%s\t\t%" PRIuPTR "\n", (void*)&uip, sizeof(uip  ), "uintptr_t", "uip  ", uip  );

    return 0;
}