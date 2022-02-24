package com.github.smallCodingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LightMatrixDDTTest {

    private LightMatrix testee;

    @BeforeEach
    public void initTestee() {
        testee = new LightMatrix();
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideTestAreas")
    public void testTurningOnAreas(LightMatrix.Area area, int expectedLightCount, String theMessage) {
        testee.turnOn(area);
        assertEquals(expectedLightCount, testee.getActiveLights(), theMessage);
    }

    private static Stream<Arguments> provideTestAreas() {
        return Stream.of(
                Arguments.of(new LightMatrix.Area(0, 0, 0, 0), 1, "From 0,0 to 0,0 => One Light"),
                Arguments.of(new LightMatrix.Area(0, 0, 999, 999), 1_000_000, "From 0,0 to 999,999 => One Million")
        );
    }


    @ParameterizedTest(name = "{2}")
    @MethodSource("provideTestAreasForTurningOff")
    public void testTurningOffAreas(LightMatrix.Area area, int expectedLightCount, String theMessage) {
        Arguments arguments = provideTestAreasForTurningOff().findFirst().get();
        LightMatrix.Area local_area = arguments.get()[0];
        testee.turnOn(new LightMatrix.Area(0, 0, 999, 999));
        testee.turnOff(area);
        assertEquals(expectedLightCount, testee.getActiveLights(), theMessage);
    }

    private static Stream<Arguments> provideTestAreasForTurningOff() {
        return Stream.of(
                Arguments.of(new LightMatrix.Area(499, 499, 500, 500), 1_000_000 - 4, "From 499,499 to 500,500 => 1_000_000 - 4")
        );
    }


}
