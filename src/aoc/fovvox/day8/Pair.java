package aoc.fovvox.day8;

public class Pair {
    Point p1;
    Point p2;

    public Pair(Point p1, Point p2) {
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
