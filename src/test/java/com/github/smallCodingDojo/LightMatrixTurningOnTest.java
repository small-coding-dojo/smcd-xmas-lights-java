package com.github.smallCodingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightMatrixTurningOnTest {
    private LightMatrix testee;

    @BeforeEach
    public void initializeLightMatrix(){
        testee = new LightMatrix();
    }

    @Test
    public void testTurningOnTwoLightsResultsInTwoActiveLights () {
        testee.turnOn(new LightMatrix.Area(0, 0, 0, 0));
        testee.turnOn(new LightMatrix.Area(1, 1, 1, 1));

        int expectedLights = 2;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);
    }

    @Test
    public void testTurningOnASquareOfLightsResultsIn4ActiveLights () {
        testee.turnOn(new LightMatrix.Area(0, 0, 1, 1));

        int expectedLights = 4;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);
    }

    @Test
    public void testTurningOnIntersectingSectionsOfLightsResultsIn4ActiveLights () {
        testee.turnOn(new LightMatrix.Area(0, 0, 1, 1));
        testee.turnOn(new LightMatrix.Area(0, 0, 0, 0));

        int expectedLights = 4;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideTestAreas")
    public void fromUnlitMatrix(LightMatrix.Area area, int expectedLightCount, String theMessage) {
        testee.turnOn(area);
        assertEquals(expectedLightCount, testee.getActiveLights(), theMessage);
    }

    private static Stream<Arguments> provideTestAreas() {
        return Stream.of(
                Arguments.of(new LightMatrix.Area(0, 0, 0, 0), 1, "From 0,0 to 0,0 => One Light"),
                Arguments.of(new LightMatrix.Area(0, 0, 999, 999), 1_000_000, "From 0,0 to 999,999 => One Million Lights"),
                Arguments.of(new LightMatrix.Area(0, 15, 0, 16), 2, "From 0,15 to 0,16 => Two Lights"),
                Arguments.of(new LightMatrix.Area(0, 0, 0, 1), 2, "From 0,0 to 0,1 => Two Lights")
        );
    }
}
