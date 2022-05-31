package com.github.small_coding_dojo.xmas_lights;

public class ChristmasLights {
    public static void main(String[] args) {
        LightMatrix christmasLights = performKataOperations();
        renderOutput(christmasLights);
    }

    private static LightMatrix performKataOperations() {
        LightMatrix result = new LightMatrix();

        result.turnOn(new Area(887, 9, 959, 629));
        result.turnOn(new Area(454, 398, 844, 448));

        result.turnOff(new Area(539, 243, 559, 965));
        result.turnOff(new Area(370, 819, 676, 868));
        result.turnOff(new Area(145, 40, 370, 997));
        result.turnOff(new Area(301, 3, 808, 453));

        result.turnOn(new Area(351, 678, 951, 908));

        result.toggle(new Area(720, 196, 897, 994));
        result.toggle(new Area(831, 394, 904, 860));

        return result;
    }

    @SuppressWarnings("java:S106") // Suppress sonar warning for logging via system.out.println
    private static void renderOutput(LightMatrix christmasLights) {
        System.out.println(christmasLights.getTotalBrightness());
    }
}
