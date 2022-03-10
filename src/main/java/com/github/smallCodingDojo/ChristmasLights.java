package com.github.smallCodingDojo;

public class ChristmasLights {
    public static void main(String[] args) {
        LightMatrix christmasLights;
        christmasLights = new LightMatrix();
        christmasLights.turnOn(new LightMatrix.Area(887,9,959,629));
        christmasLights.turnOn(new LightMatrix.Area(454,398,844,448));

        christmasLights.turnOff(new LightMatrix.Area(539,243,559,965));
        christmasLights.turnOff(new LightMatrix.Area(370,819,676,868));
        christmasLights.turnOff(new LightMatrix.Area(145,40,370,997));
        christmasLights.turnOff(new LightMatrix.Area(301,3,808,453));

        christmasLights.turnOn(new LightMatrix.Area(351,678,951,908 ));

        christmasLights.toggle(new LightMatrix.Area(720,196 , 897,994));
        christmasLights.toggle(new LightMatrix.Area(831,394, 904,860));

        System.out.println(christmasLights.getActiveLights());
    }
}
