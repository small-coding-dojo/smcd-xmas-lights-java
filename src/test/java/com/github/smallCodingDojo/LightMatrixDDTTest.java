package com.github.smallCodingDojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LightMatrixDDTTest {

    private final LightMatrix testee;
    private final LightMatrix.Area area;
    private final int expectedLightCount;


    @Parameterized.Parameters(name="{2}")
    public static Collection<Object[]> dataProvider(){
        return Arrays.asList(new Object[][]{
                {new LightMatrix.Area(0,0,0,0), 1, "From 0,0 to 0,0 => One Light"},
                {new LightMatrix.Area(0,0,999,999), 1_000_000, "From 0,0 to 999,999 => One Million"}
        });
    }

    public LightMatrixDDTTest (LightMatrix.Area area, int expectedLightCount, String unusedButNecessaryForJUnit4) {
        testee = new LightMatrix();
        this.area = area;
        this.expectedLightCount = expectedLightCount;
    }

    @Test
    public void testTurningOnAreas() {
        testee.turnOn(area);
        assertEquals(expectedLightCount, testee.getActiveLights());
    }


}
