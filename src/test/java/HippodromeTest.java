import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    static Hippodrome hippodrome;
    static Hippodrome hippodrome2;
    static List horseList;

    @BeforeAll
    static void hippodromeSet() {
        horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Horse", 1.5+ (double) i /10));

        }

        hippodrome = new Hippodrome(horseList);

    }

    @Test
    void Hippodrome() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null)).getMessage());
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.",
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>())).getMessage());
    }

    @Test
    void getHorses() {
        assertEquals(hippodrome.getHorses(),horseList);
    }

    @Test
    void move() {
        List <Horse> horseList1 = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horseList1.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horseList1).move();
        for(Horse horse : horseList1){
            Mockito.verify(horse).move();

        }


    }

    @Test
    void getWinner() {
        Horse hippodromeWinner = hippodrome.getWinner();
        Object win = horseList.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(hippodromeWinner,win);
    }
}