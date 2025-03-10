import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    static Horse horseFirst;
    static Horse horseSecond;
    static String nameHorse = "Plotva";
    static Double speedHorse = 15.1;
    final static String probName = " ,15.1";
    final static String tabName = "\t,15.1";
    static Double distanceHorse = 49.7;


    @Test
    void horse(){
        assertThrows(IllegalArgumentException.class, ()-> new Horse(null,15.0));
        assertEquals("Name cannot be null.", assertThrows(IllegalArgumentException.class, ()-> new Horse(null,15.0)).getMessage());
        assertThrows(IllegalArgumentException.class, ()-> new Horse("",15.0));
        assertThrows(IllegalArgumentException.class, ()-> new Horse(nameHorse,-1.5));
        assertEquals("Speed cannot be negative.", assertThrows(IllegalArgumentException.class, ()-> new Horse(nameHorse,-1.5)).getMessage());
        assertThrows(IllegalArgumentException.class,()-> new Horse(nameHorse,speedHorse,-5));
        assertEquals("Distance cannot be negative.",assertThrows(IllegalArgumentException.class,()-> new Horse(nameHorse,speedHorse,-5)).getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            ",15.1",probName,tabName
    })
    void csvSourceHorse(String name, Double speed){
        assertThrows(IllegalArgumentException.class,()-> new Horse(name,speed));

    }

    @BeforeAll
    static  void setHorse(){
        horseFirst = new Horse(nameHorse,speedHorse);
        horseSecond = new Horse(nameHorse,speedHorse,distanceHorse);
    }
    @Test
    void getName() {
        assertEquals(nameHorse, horseFirst.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(speedHorse, horseFirst.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(0,horseFirst.getDistance());
        assertEquals(distanceHorse, horseSecond.getDistance());
    }

//    @Test
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.7, 0.9})
    void testMoveWithParameterized(double mockRandomValue) {

        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandomValue);
            Horse horse = new Horse("Roach", 16.0, 10.0);
            // Выполняем метод move
            horse.move();
            assertEquals(10.0 + 16.0 * mockRandomValue, horse.getDistance());
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}