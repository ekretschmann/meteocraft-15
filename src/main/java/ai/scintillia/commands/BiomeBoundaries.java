package ai.scintillia.commands;

import java.util.Objects;

public class BiomeBoundaries {
    private long topLeftX;
    private long topLeftY;
    private long bottomRightX;
    private long bottomRightY;
    private String biome;

    public BiomeBoundaries(long topLeftX, long topLeftY, long bottomRightX, long bottomRightY, String biome) {

        this.topLeftX=topLeftX;
        this.topLeftY=topLeftY;
        this.bottomRightX=bottomRightX;
        this.bottomRightY=bottomRightY;
        this.biome=biome;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiomeBoundaries that = (BiomeBoundaries) o;
        return topLeftX == that.topLeftX &&
                topLeftY == that.topLeftY &&
                bottomRightX == that.bottomRightX &&
                bottomRightY == that.bottomRightY &&
                Objects.equals(biome, that.biome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeftX, topLeftY, bottomRightX, bottomRightY, biome);
    }

    @Override
    public String toString() {
        return "BiomeBoundaries{" +
                "topLeftX=" + topLeftX +
                ", topLeftY=" + topLeftY +
                ", bottomRightX=" + bottomRightX +
                ", bottomRightY=" + bottomRightY +
                ", biome='" + biome + '\'' +
                '}';
    }

    public long getMinX() {
        return topLeftX;
    }

    public long getMaX() {
        return bottomRightX;
    }

    public long getMinY() {
        return bottomRightY;
    }

    public long getMaxY() {
        return topLeftY;
    }
}
