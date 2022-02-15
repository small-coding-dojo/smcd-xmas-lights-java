package com.github.smallCodingDojo;


public class LightMatrix
{
    public static final boolean LIGHT_ON = true;
    public static final boolean LIGHT_OFF = false;
    private boolean[][] lights = initializeMatrix();

    private static boolean[][] initializeMatrix() {
        boolean[][] result = new boolean[1000][1000];
        return result;
    }

    public int getActiveLights() {
        int activeLightCount = 0;
        for (int x = 0; x< 1000; x++) {
            for (int y = 0; y< 1000; y++) {
                activeLightCount += lights[x][y] ? 1 : 0;
            }
        }
        return activeLightCount;
    }

    // TODO: Consider using an iterator on Area / the visitor pattern / stream processing
    public void turnOn(Area area) {
        turn(area, LIGHT_ON);
    }

    public void turnOff(Area area) {
        turn(area, LIGHT_OFF);
    }

    private void turn(Area area, boolean state) {
        for(int x = area.getX1(); x<= area.getX2(); x++) {
            for(int y = area.getY1(); y<= area.getY2(); y++) {
                lights[x][y] = state;
            }
        }
    }

    public void toggle(Area area) {
        for(int x = area.getX1(); x<= area.getX2(); x++) {
            for(int y = area.getY1(); y<= area.getY2(); y++) {
                lights[x][y] = ! lights[x][y];
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
}
