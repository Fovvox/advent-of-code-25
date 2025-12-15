package aoc.fovvox.day8;

public class Point {
    int x;
    int y;
    int z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {
    }

    double distance(Point p) {
        return Math.sqrt(
                Math.pow(p.x - this.x, 2) +
                        Math.pow(p.y - this.y, 2) +
                        Math.pow(p.z - this.z, 2)
        );
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}
