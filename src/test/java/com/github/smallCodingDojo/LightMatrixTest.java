package com.github.smallCodingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LightMatrixTest
{

    private LightMatrix testee;

    @BeforeEach
    public void initializeLightMatrix(){
        testee = new LightMatrix();
    }

    @Test
    public void shouldActiveLightsReturnZero()
    {        
        int expectedLights = 0;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);
    }



    @Test
    public void turnOneLightOnAndOffAgain() 
    {
        testee.turnOn(new LightMatrix.Area(0, 0, 0, 0));
        testee.turnOff(new LightMatrix.Area(0, 0, 0, 0));

        int expectedLights = 0;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);

    }



    @Test
    public void testTurningOnIntersectingSectionsOfLightsResultsIn4ActiveLightsAndTurningOneOff () {
        testee.turnOn(new LightMatrix.Area(0, 0, 1, 1));

        int expectedLights = 4;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);

        testee.turnOff(new LightMatrix.Area(0, 0, 0, 0));

        assertEquals(3, testee.getActiveLights());

    }

    @Test
    public void testTurningOn3LightsThenToggle1000ResultsIn997ActiveLights() {
        testee.turnOn(new LightMatrix.Area(0,0,0, 0));
        testee.turnOn(new LightMatrix.Area(2,0,2, 0));
        testee.turnOn(new LightMatrix.Area(999,0,999, 0));

        assertEquals(3, testee.getActiveLights(), "precondition failed");

        testee.toggle(new LightMatrix.Area(0,0,999, 0));

        assertEquals(997, testee.getActiveLights());
    }

    @Test
    public void testTurningOnSomeLightsThenToggleNotAllLightsResultsInPlausibleNumberOfLightsTurnedOn() {
        // Turn on three lights in row zero
        testee.turnOn(new LightMatrix.Area(0,0,0, 0));
        testee.turnOn(new LightMatrix.Area(2,0,2, 0));
        testee.turnOn(new LightMatrix.Area(999,0,999, 0));

        // Turn on one light in row two
        testee.turnOn(new LightMatrix.Area(2,998,2, 998));

        // Check that four lights turned on
        assertEquals(4, testee.getActiveLights(), "precondition failed");

        // Toggle 1000 lights in row 0
        testee.toggle(new LightMatrix.Area(0,0,999, 0));

        // Check (1000 - 3) + 1 = 998 lights are turned on
        assertEquals(998, testee.getActiveLights());
    }


    @Test
    public void testTurnOffActiveLightsTurnsOff() {
        LightMatrix.Area area = new LightMatrix.Area(499, 499, 500, 500);
        int expectedLightCount = 1_000_000 - 4;

        testee.turnOn(new LightMatrix.Area(0, 0, 999, 999));
        testee.turnOff(area);

        assertEquals(expectedLightCount, testee.getActiveLights(), "Completely lit matrix, turn off from 499,499 to 500,500 => 1_000_000 - 4");
    }

    @Test
    public void testTurnOffMixedLightsTurnsOff() {
        LightMatrix.Area area = new LightMatrix.Area(499, 499, 500, 500);
        int expectedLightCount = 500_000 - 2;

        testee.turnOn(new LightMatrix.Area(0, 0, 999, 499));

        assertEquals(500_000, testee.getActiveLights(), "Precondition not met.");

        testee.turnOff(area);

        assertEquals(expectedLightCount, testee.getActiveLights(), "Partially lit matrix, turn off from 499,499 to 500,500 => 500_000 - 2");
    }

    @Test
    public void testTurnOffDeactivatedLightsLeavesOff(){
        LightMatrix.Area area = new LightMatrix.Area(499, 499, 500, 500);
        int expectedLightCount = 0;

        testee.turnOff(area);

        assertEquals(expectedLightCount, testee.getActiveLights(), "Unlit matrix, turn off from 499,499 to 500,500 => 0");
    }

//    @Test
//    void testOverlappingLightToggling() {
//        LightMatrix.Area area = new LightMatrix.Area(0,0,1,1);
//
//        testee.toggle(area);
//
//        testee.toggle(new LightMatrix.Area(0,1,1,2));
//
//        // asummed lights
//        // 000   &  110  => 101
//        // 000   &  110  => 101
//
//        testee.toggle(new LightMatrix.Area(0,1,1,2));
//
//        assertEquals(/*expected*/0, /*actual*/ 1,"Reason");
//    }

    @Test
    void testOneLightByPosition() {
        LightMatrix.Area area = new LightMatrix.Area(0, 0, 0, 0);

        testee.turnOn(area);
        assertTrue(testee.isLit(0,0));
    }

    @Test
    void testAnotherLightByPosition() {
        LightMatrix.Area area = new LightMatrix.Area(1, 1, 2, 1);

        // X: 0 1 2
        // Y
        // 0  0 0 0
        // 1  0 1 1
        // 2  0 0 0

        testee.turnOn(area);
        for (int x=area.getX1(); x< area.getX2();x++) {
            for (int y=area.getY1(); y<area.getY2(); y++) {
                assertTrue(testee.isLit(x,y));
            }
        }
    }
}
