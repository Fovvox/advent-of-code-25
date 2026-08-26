package aoc.fovvox.day9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Point2D;
import aoc.fovvox.Util;

public class Main {

    //pert 2: 4601733120 -> too high
    //part 2: 1530527040 ??
    static void main() throws IOException {
        final List<String> parts = Util.parseLines("inputs/day9.txt");
        final List<Point2D> points = parsePoints(parts);
        final ExecutionTimer executionTimer = new ExecutionTimer();
        executionTimer.start();
        final long result1 = part1(points);
        executionTimer.stop();
        System.out.println("Part 1: " + result1);
        System.out.println(executionTimer.getFormatted());
        executionTimer.start();
        final long result2 = part2(points);
        executionTimer.stop();
        System.out.println("Part 2: " + result2);
        System.out.println(executionTimer.getFormatted());
    }

    private static long part2(final List<Point2D> points) {
        long maxArea = 0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                final Point2D p1 = points.get(i);
                final Point2D p2 = points.get(j);
                if (fitsInsidePolygon(p1, p2, points) && maxArea < area(p1, p2)) {
                        System.out.println(p1 + " - " + p2 + " -> " + area(p1, p2));
                        maxArea =area(p1,p2);
                    }
            }
        }
        return maxArea;
    }

    /**
     * Checks that the axis-aligned rectangle with opposite corners p1, p2 lies
     * fully inside the rectilinear polygon defined by (points, in order, closed).
     * <p>
     * Two conditions must hold:
     * 1) no polygon edge cuts through the OPEN interior of the rectangle;
     * 2) the rectangle's center point is inside the polygon.
     * If (1) holds, every point of the open interior shares the same inside/outside
     * classification, so checking the center alone is enough.
     */
    private static boolean fitsInsidePolygon(final Point2D p1, final Point2D p2, final List<Point2D> points) {
        final long x1 = Math.min(p1.getX(), p2.getX());
        final long x2 = Math.max(p1.getX(), p2.getX());
        final long y1 = Math.min(p1.getY(), p2.getY());
        final long y2 = Math.max(p1.getY(), p2.getY());

        if (x1 == x2 || y1 == y2) {
            return false;
        }

        final int n = points.size();
        for (int i = 0; i < n; i++) {
            final Point2D a = points.get(i);
            final Point2D b = points.get((i + 1) % n);

            if (a.getX() == b.getX()) {
                // vertical edge
                final long ex = a.getX();
                final long ey1 = Math.min(a.getY(), b.getY());
                final long ey2 = Math.max(a.getY(), b.getY());
                if (ex > x1 && ex < x2 && Math.max(ey1, y1) < Math.min(ey2, y2)) {
                    return false;
                }
            } else {
                // horizontal edge
                final long ey = a.getY();
                final long ex1 = Math.min(a.getX(), b.getX());
                final long ex2 = Math.max(a.getX(), b.getX());
                if (ey > y1 && ey < y2 && Math.max(ex1, x1) < Math.min(ex2, x2)) {
                    return false;
                }
            }
        }

        final double centerX = (x1 + x2) / 2.0;
        final double centerY = (y1 + y2) / 2.0;
        return isInsidePolygon(centerX, centerY, points);
    }

    /**
     * Standard ray-casting point-in-polygon test (PNPOLY). Horizontal edges are
     * skipped and each edge is treated as half-open on the y-axis, which makes the
     * result robust when the ray passes exactly through a vertex.
     */
    private static boolean isInsidePolygon(final double px, final double py, final List<Point2D> points) {
        boolean inside = false;
        final int n = points.size();
        for (int i = 0, j = n - 1; i < n; j = i++) {
            final Point2D a = points.get(i);
            final Point2D b = points.get(j);
            final double ax = a.getX();
            final double ay = a.getY();
            final double bx = b.getX();
            final double by = b.getY();

            if ((ay > py) != (by > py)) {
                final double xIntersect = ax + (py - ay) / (by - ay) * (bx - ax);
                if (px < xIntersect) {
                    inside = !inside;
                }
            }
        }
        return inside;
    }

    private static long part1(final List<Point2D> points) {
        long maxArea = 0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                maxArea = Math.max(maxArea, area(points.get(i), points.get(j)));
            }
        }
        return maxArea;
    }

    private static List<Point2D> parsePoints(final List<String> parts) {
        final List<Point2D> point2DList = new ArrayList<>();
        for (final String part : parts) {
            final String[] nums = part.split(",");
            final int x = Integer.parseInt(nums[0]);
            final int y = Integer.parseInt(nums[1]);
            point2DList.add(new Point2D(x, y));
        }
        return point2DList;
    }

    private static long area(final Point2D point1, final Point2D point2) {
        final long width = Math.abs(point1.getX() - point2.getX()) + 1;
        final long heigh = Math.abs(point1.getY() - point2.getY()) + 1;
        return width * heigh;
    }
}