package com.github.smallCodingDojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LightMatrixTest 
{

    private LightMatrix testee;
    @Before
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
    public void turningOnOneLightReturnsOneActiveLight()
    {        
        testee.turnOn(new LightMatrix.Area(0, 0, 0, 0));

        int expectedLights = 1;
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
    public void testTurningOnIntersectingSectionsOfLightsResultsIn4ActiveLightsAndTurningOneOff () {
        testee.turnOn(new LightMatrix.Area(0, 0, 1, 1));

        int expectedLights = 4;
        int actualLights = testee.getActiveLights();

        assertEquals(expectedLights, actualLights);

        testee.turnOff(new LightMatrix.Area(0, 0, 0, 0));

        assertEquals(3, testee.getActiveLights());

    }


}
