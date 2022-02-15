package com.github.smallCodingDojo;


public class LightMatrix
{
    private final boolean[][] lights = new boolean[1000][1000];

    public int getActiveLights() {
        int activeLightCount = 0;
        for (int x = 0; x< 1000; x++) {
            for (int y = 0; y< 1000; y++) {
                activeLightCount += lights[x][y] ? 1 : 0;
            }
        }
        return activeLightCount;
    }

    public void turnOn(Area area) {
        applyCommandToArea(new Lighter(), area);
    }

    public void turnOff(Area area) {
        applyCommandToArea(new Extinguisher(), area);
    }

    public void toggle(Area area) {
        applyCommandToArea(new LightToggler(), area);
    }

    private void applyCommandToArea(LightStateChanger theCommand, Area area) {
        for(int x = area.getX1(); x<= area.getX2(); x++) {
            for(int y = area.getY1(); y<= area.getY2(); y++) {
                lights[x][y] = theCommand.execute( lights[x][y]);
            }
        }
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
            return "("+this.x1+","+this.y1+")";
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

    private static class LightToggler implements LightStateChanger {
        @Override
        public boolean execute(boolean lightState) {
            return !lightState;
        }
    }

    private static class Lighter implements LightStateChanger {
        @Override
        public boolean execute(boolean lightState) {
            return true;
        }
    }

    private static class Extinguisher implements LightStateChanger {
        @Override
        public boolean execute(boolean lightState) {
            return false;
        }
    }

}
