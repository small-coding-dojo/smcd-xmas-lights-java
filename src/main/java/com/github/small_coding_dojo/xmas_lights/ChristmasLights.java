package com.github.small_coding_dojo.xmas_lights;

public class ChristmasLights {
    public static void main(String[] args) {
        LightMatrix christmasLights = new LightMatrix();

        christmasLights.turnOn(new Area(887, 9, 959, 629));
        christmasLights.turnOn(new Area(454, 398, 844, 448));

        christmasLights.turnOff(new Area(539, 243, 559, 965));
        christmasLights.turnOff(new Area(370, 819, 676, 868));
        christmasLights.turnOff(new Area(145, 40, 370, 997));
        christmasLights.turnOff(new Area(301, 3, 808, 453));

        christmasLights.turnOn(new Area(351, 678, 951, 908));

        christmasLights.toggle(new Area(720, 196, 897, 994));
        christmasLights.toggle(new Area(831, 394, 904, 860));

        renderOutput(christmasLights);
    }

    @SuppressWarnings("java:S106") // Suppress sonar warning for logging via system.out.println
    private static void renderOutput(LightMatrix christmasLights) {
        System.out.println(christmasLights.getTotalBrightness());
    }
}
