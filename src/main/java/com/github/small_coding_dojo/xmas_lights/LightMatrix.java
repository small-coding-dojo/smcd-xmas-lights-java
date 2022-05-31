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

    static class Area {
        private final int x1;
        private final int y1;
        private final int x2;
        private final int y2;

        Area(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public String toString() {
            return "(" + this.x1 + "," + this.y1 + ")";
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }
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
