package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GradeCalculatorTest {

    // 1
    @ParameterizedTest
    @CsvSource({
        "95, A",
        "85, B",
        "75, C",
        "65, D",
        "30, F"
    })
    @DisplayName("Ердийн оноонууд зөв үсгэн дүн буцаах ёстой")
    void letterGradeNormalValues(double score, String expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String actual = calc.letterGrade(score);

        // Assert
        assertEquals(expected, actual);
    }

    // 2
    @ParameterizedTest
    @CsvSource({
        "90, A",
        "89.99, B",
        "60, D",
        "59.99, F",
        "0, F",
        "100, A"
    })
    @DisplayName("Үсгэн дүнгийн хязгаарын утгууд зөв байх ёстой")
    void letterGradeBoundaryValues(double score, String expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String actual = calc.letterGrade(score);

        // Assert
        assertEquals(expected, actual);
    }

    // 3
    @Test
    @DisplayName("-1 оноо IllegalArgumentException үүсгэх ёстой")
    void letterGradeBelowZeroThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> calc.letterGrade(-1)
        );
    }

    // 4
    @Test
    @DisplayName("101 оноо IllegalArgumentException үүсгэх ёстой")
    void letterGradeAboveHundredThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> calc.letterGrade(101)
        );
    }

    // 5
    @Test
    @DisplayName("Хамгийн их зөв оноонуудын нийлбэр 100 байх ёстой")
    void totalScoreMaximumValues() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double actual = calc.totalScore(10, 40, 10, 10, 30);

        // Assert
        assertEquals(100.0, actual);
    }

    // 6
    @ParameterizedTest
    @CsvSource({
        "10, 40, 10, 10, 30, 100",
        "5, 20, 5, 5, 15, 50",
        "0, 0, 0, 0, 0, 0",
        "8, 30, 7, 9, 25, 79"
    })
    @DisplayName("totalScore зөв нийлбэр тооцох ёстой")
    void totalScoreValidValues(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected) {

        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double actual = calc.totalScore(
            att,
            lab,
            quiz1,
            quiz2,
            exam
        );

        // Assert
        assertEquals(expected, actual);
    }

    // 7
    @Test
    @DisplayName("Сөрөг ирцийн оноо exception үүсгэх ёстой")
    void totalScoreNegativeAttendanceThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> calc.totalScore(-5, 20, 5, 5, 20)
        );
    }

    // 8
    @Test
    @DisplayName("Лабын оноо 40-өөс их бол exception үүсгэх ёстой")
    void totalScoreLabAboveMaximumThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act + Assert
        assertThrows(
            IllegalArgumentException.class,
            () -> calc.totalScore(10, 41, 10, 10, 30)
        );
    }
}