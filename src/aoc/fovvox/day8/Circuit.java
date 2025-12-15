package aoc.fovvox.day8;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Circuit {
    long id;
    Set<Point> points;

    public Circuit(long id, Point init) {
        this.id = id;
        points = new HashSet<>();
        points.add(init);
    }
    
    public void addAll(Circuit circuit) {
        points.addAll(circuit.points);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Circuit circuit = (Circuit) o;
        return id == circuit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    public boolean contains(Point point) {
        return points.contains(point);
    }

    public int size() {
        return points.size();
    }

    @Override
    public String toString() {
        return points.toString();
    }
}
