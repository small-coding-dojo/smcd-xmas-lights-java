package com.github.smallCodingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LightMatrixDDTTest {

    private LightMatrix testee;

    @BeforeEach
    public void initTestee () {
        testee = new LightMatrix();
    }

    @ParameterizedTest
    @MethodSource("provideTestAreas")
    public void testTurningOnAreas(LightMatrix.Area area, int expectedLightCount) {
        testee.turnOn(area);
        assertEquals(expectedLightCount, testee.getActiveLights());
    }

    private static Stream<Arguments> provideTestAreas() {
        return Stream.of (
                Arguments.of(new LightMatrix.Area(0,0,0,0), 1)
        );
    }


}
