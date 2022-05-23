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
    public void turnOnTwoLights() {
        testee.turnOn(new LightMatrix.Area(0, 0, 0, 0));
        testee.turnOn(new LightMatrix.Area(1, 1, 1, 1));

        int expectedBrightness = 2;
        int actualBrightness = testee.getTotalBrightness();

        assertEquals(expectedBrightness, actualBrightness);
    }

    @Test
    public void turnOnASquareOfLights() {
        testee.turnOn(new LightMatrix.Area(0, 0, 1, 1));

        int expectedBrightness = 4;
        int actualBrightness = testee.getTotalBrightness();

        assertEquals(expectedBrightness, actualBrightness);
    }

    @Test
    public void turningOnIntersectingSections () {
        testee.turnOn(new LightMatrix.Area(0, 0, 1, 1));
        testee.turnOn(new LightMatrix.Area(0, 0, 0, 0));

        int expectedBrightness = 5;
        int actualBrightness = testee.getTotalBrightness();

        assertEquals(expectedBrightness, actualBrightness);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideTestAreas")
    public void fromUnlitMatrix(LightMatrix.Area area, int expectedBrightness, String theMessage) {
        testee.turnOn(area);
        assertEquals(expectedBrightness, testee.getTotalBrightness(), theMessage);
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
