package aoc.fovvox.day8;

import aoc.fovvox.Point3D;

public class Pair {
    Point3D p1;
    Point3D p2;

    public Pair(Point3D p1, Point3D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double distance() {
        return p1.distance(p2);
    }
    

    @Override
    public String toString() {
        return "{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
