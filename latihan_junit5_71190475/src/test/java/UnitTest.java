import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class UnitTest {
    static LatihanJunit5 latihanJunit5;

    @BeforeAll
    static void init(){
        latihanJunit5 = new LatihanJunit5();
    }

    @AfterAll
    static void destroy(){
        latihanJunit5 = null;
    }

    // start EC
    private static Stream<Arguments> EC(){
        return Stream.of(
                Arguments.of(0, 2000000),
                Arguments.of(10, 10000000),
                Arguments.of(22, 25000000),
                Arguments.of(40f, 50000000),
                Arguments.of(-1, -1000000),
                Arguments.of(-1, 1000000000000d)
        );
    }


    @ParameterizedTest
    @MethodSource("EC")
    void PajakEC(double expected, double salary){
        Assertions.assertEquals(expected, latihanJunit5.getPajak(salary));
    }
    // end EC

    // start BVA EC1
    private static Stream<Arguments> BVAEC1(){
        return Stream.of(
                Arguments.of(false, -1),
                Arguments.of(true, 0),
                Arguments.of(true, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("BVAEC1")
    void PajakBVAEC1(boolean expected, double salary){
        Assertions.assertEquals(expected, latihanJunit5.getPajak(salary) == 0);
    }
    // end BVA EC1

    // start BVA EC1 dan EC2
    private static Stream<Arguments> BVAEC1EC2(){
        return Stream.of(
                Arguments.of(true, 3999999),
                Arguments.of(true, 4000000),
                Arguments.of(false, 4000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVAEC1EC2")
    void PajakBVAEC1EC2(boolean expected, double salary){
        Assertions.assertEquals(expected, latihanJunit5.getPajak(salary) == 0);
    }
    // end BVA EC1 dan EC2

    // start BVA EC2 dan EC3
    private static Stream<Arguments> BVAEC2EC3(){
        return Stream.of(
                Arguments.of(true, 14999999),
                Arguments.of(true, 15000000),
                Arguments.of(false, 15000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVAEC2EC3")
    void PajakBVAEC2EC3(boolean expected, double salary){
        Assertions.assertEquals(expected, latihanJunit5.getPajak(salary) == 10);
    }
    // end BVA EC2 dan EC3

    // start BVA EC3 dan EC4
    private static Stream<Arguments> BVAEC3EC4(){
        return Stream.of(
                Arguments.of(true, 39999999),
                Arguments.of(true, 40000000),
                Arguments.of(false, 40000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVAEC3EC4")
    void PajakBVAEC3EC4(boolean expected, double salary){
        Assertions.assertEquals(expected, latihanJunit5.getPajak(salary) == 22);
    }
    // end BVA EC3 dan EC4

    // start BVA EC4
    private static Stream<Arguments> BVAEC4(){
        return Stream.of(
                Arguments.of(true, 999999999998f),
                Arguments.of(true, 999999999999f),
                Arguments.of(false, 1000000000000d)
        );
    }

    @ParameterizedTest
    @MethodSource("BVAEC4")
    void PajakBVAEC4(boolean expected, double salary){
        Assertions.assertEquals(expected, latihanJunit5.getPajak(salary) == 40f);
    }
    // end BVA EC4
}
