# Lab 04 — Нэгжийн тестийн эхлэл (JUnit 5)

## Оюутны мэдээлэл

- Нэр: Ө. Анар
- Оюутны код: B242270129
- Хичээл: F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест

## 1. Зорилго

Энэ лабораторийн ажлаар Maven-д суурилсан Java төсөл дээр JUnit 5 ашиглан нэгжийн тест бичиж, assertion, exception test, boundary value болон parameterized test ашиглах дадлага хийв. Мөн зориудын mutation хийж тестүүд алдааг илрүүлж чадаж байгаа эсэхийг шалгав.

## 2. Ашигласан орчин

### Java

```text
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment (build 17.0.20.1+1-1-26.04-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.20.1+1-1-26.04-Ubuntu, mixed mode, sharing)
```

### Maven

```text
Apache Maven 3.9.12
Maven home: /usr/share/maven
Java version: 17.0.20.1, vendor: Ubuntu
```

### Ашигласан технологи

- **OpenJDK 17** — Java хөгжүүлэлтийн орчин
- **Apache Maven** — dependency болон build удирдлага
- **JUnit 5 (Jupiter)** — unit test framework
- **Git / GitHub** — source code болон commit history хадгалах
- **Ubuntu Linux** — лабораторийн үндсэн орчин
- **ChatGPT (OpenAI)** — лабораторийн ажлын дараалал төлөвлөх, тестийн бүтэц боловсруулах, үр дүнг тайлбарлах болон README найруулгад туслах хэрэгсэл болгон ашигласан

## 3. GradeCalculator

`GradeCalculator` класс нь дараах хоёр үндсэн method-той.

### `letterGrade(double score)`

| Оноо | Үсгэн дүн |
|---:|:---|
| 90–100 | A |
| 80–89.99 | B |
| 70–79.99 | C |
| 60–69.99 | D |
| 0–59.99 | F |

`score` нь 0–100 хязгаараас гарвал `IllegalArgumentException` шиднэ.

### `totalScore(...)`

Нийлбэр оноог дараах бүтэцтэй тооцно:

| Үзүүлэлт | Дээд оноо |
|---|---:|
| Ирц | 10 |
| Лаб + бие даалт | 40 |
| Сорил 1 | 10 |
| Сорил 2 | 10 |
| Шалгалт | 30 |
| **Нийт** | **100** |

Аль нэг утга сөрөг эсвэл дээд хязгаараасаа хэтэрвэл `IllegalArgumentException` шиднэ.

## 4. Unit Test

`GradeCalculatorTest` класс дотор нийт **8 тестийн метод** бичсэн.

Тестүүдэд:

- `@Test`
- `@ParameterizedTest`
- `@CsvSource`
- `@DisplayName`
- `assertEquals`
- `assertThrows`

ашигласан.

### Шалгасан boundary утгууд

`letterGrade()` дээр:

```text
90, 89.99, 60, 59.99, 0, 100
```

Буруу оролтууд:

```text
-1, 101
```

`totalScore()` дээр:

- зөв нийлбэр оноо
- хамгийн их 100 оноо
- сөрөг `att`
- `lab = 41` буюу дээд хязгаараас хэтэрсэн утга

зэрэг тохиолдлуудыг шалгасан.

## 5. Parameterized Test

`letterGrade` болон `totalScore` хоёрт parameterized test ашигласан.

`@CsvSource`-ийн мөр бүр Maven дээр тусдаа test case гэж тоологддог тул 8 тестийн методтой боловч нийт ажилласан test case-ийн тоо **20** болсон.

## 6. Green Test Result

Үндсэн тестийн гаралт:

```text
Tests run: 20, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Иймээс бүх unit test амжилттай ажилласан.

Бүтэн гаралт:

```text
results/mvn-test.txt
```

## 7. Mutation Test

`GradeCalculator.letterGrade()` доторх:

```java
score >= 90
```

нөхцөлийг зориудаар:

```java
score > 90
```

болгон өөрчилж mutation test хийсэн.

Ингэснээр яг **90 оноо** өмнө нь `A` байсан бол mutant хувилбарт `B` болж, boundary test мутацийг илрүүлсэн.

Mutation test-ийн үр дүнд:

```text
BUILD FAILURE
There are test failures.
```

гарсан.

Бүтэн mutation output:

```text
results/mvn-test-mutant.txt
```

Mutation test-ийн дараа `score >= 90` нөхцөлийг буцааж зөв болгож, `mvn test`-ийг дахин ажиллуулахад:

```text
Tests run: 20, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

гарсан.

## 8. Results Files

```text
results/
├── mvn-test.txt
└── mvn-test-mutant.txt
```

## 9. Project Structure

```text
lab04-junit/
├── pom.xml
├── README.md
├── .gitignore
├── results/
│   ├── mvn-test.txt
│   └── mvn-test-mutant.txt
└── src/
    ├── main/
    │   └── java/
    │       └── mn/edu/must/sqat/
    │           └── GradeCalculator.java
    └── test/
        └── java/
            └── mn/edu/must/sqat/
                └── GradeCalculatorTest.java
```

`target/` хавтас `.gitignore` дотор орсон.

## 10. Дүгнэлт

Энэ лабораторийн ажлаар JUnit 5 ашиглан нэгжийн тестийг бодитоор хэрэгжүүлж сурсан. `GradeCalculator` классын ердийн утга, boundary value болон буруу оролтуудыг тус тус шалгасан. Нийт 8 тестийн метод бичсэн бөгөөд parameterized test-ийн өгөгдлүүдтэй нийлээд Maven дээр 20 test case ажилласан. Бүх үндсэн тест `Failures: 0, Errors: 0` үр дүнтэйгээр `BUILD SUCCESS` болсон. Хамгийн сонирхолтой тест нь 90 онооны boundary case байсан. `>= 90` нөхцөлийг зориуд `> 90` болгож мутаци хийхэд энэ тест алдааг илрүүлж `BUILD FAILURE` үүсгэсэн. Ингэснээр зөвхөн тест PASS болохоос гадна boundary нөхцөлүүдийг зөв сонгох нь тестийн чанарт чухал гэдгийг харуулсан.
