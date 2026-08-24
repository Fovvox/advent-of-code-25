package aoc.fovvox;

public class Point3D {
    int x;
    int y;
    int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D() {
    }

    public double distance(Point3D p) {
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
