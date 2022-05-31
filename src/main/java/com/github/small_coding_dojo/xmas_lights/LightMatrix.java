package com.github.small_coding_dojo.xmas_lights;


import java.util.Arrays;

public class LightMatrix {
    private final int[][] brightnesses = new int[1000][1000];

    public int getTotalBrightness() {
        return Arrays.stream(brightnesses)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    public void turnOn(Area area) {
        applyCommandToArea(new Brightener(), area);
    }

    public void turnOff(Area area) {
        applyCommandToArea(new Dimmer(), area);
    }

    public void toggle(Area area) {
        applyCommandToArea(new BrightnessToggler(), area);
    }

    private void applyCommandToArea(LightBrightnessChanger theCommand, Area area) {
        for (int x = area.getX1(); x <= area.getX2(); x++) {
            for (int y = area.getY1(); y <= area.getY2(); y++) {
                brightnesses[x][y] = theCommand.execute(brightnesses[x][y]);
            }
        }
    }

    public boolean isLit(int x, int y) {
        return brightnesses[x][y] > 0;
    }

    private static class BrightnessToggler implements LightBrightnessChanger {
        @Override
        public int execute(int brightness) {
            return brightness + 2;
        }
    }

    private static class Brightener implements LightBrightnessChanger {
        @Override
        public int execute(int brightness) {
            return brightness + 1;
        }
    }

    private static class Dimmer implements LightBrightnessChanger {
        @Override
        public int execute(int brightness) {
            return Math.max(0, brightness - 1);
        }
    }
}
