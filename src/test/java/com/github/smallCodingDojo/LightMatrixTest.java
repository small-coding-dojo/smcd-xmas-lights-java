package com.github.smallCodingDojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void turningOnTwoLightsReturnsTwoActiveLights()
    {
        testee.turnOn(new LightMatrix.Area(0, 0, 0, 1));

        int expectedLights = 2;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);
    }

    @Test
    public void turningOnTwoOtherLightsReturnsTwoActiveLights()
    {
        testee.turnOn(new LightMatrix.Area(0, 15, 0, 16));

        int expectedLights = 2;
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
    public void testTurningOn1000x1000LightsResultsInOneMillion() {
        testee.turnOn(new LightMatrix.Area(0,0,999, 999));
        assertEquals(1_000_000, testee.getActiveLights());
    }

    @Test
    public void testTurningOn5LightsThenToggleResultsInBLABLABLUBB() {
        testee.turnOn(new LightMatrix.Area(0,0,0, 0));
        testee.turnOn(new LightMatrix.Area(2,0,2, 0));
        testee.turnOn(new LightMatrix.Area(999,0,999, 0));

        assertEquals(3, testee.getActiveLights());

        testee.toggle(new LightMatrix.Area(0,0,999, 0));

        assertEquals(997, testee.getActiveLights());
    }

}
