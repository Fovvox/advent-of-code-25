package aoc.fovvox.day8;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

import java.io.IOException;
import java.util.*;

public class Main {
    static void main() throws IOException {
        List<String> parts = Util.parseLines("inputs/day8.txt");
        List<Point> points = parsePoints(parts);
        List<Pair> pairs = buildPairs(points);
        Set<Circuit> circuits = buildCircuits(points);
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        long result = part1(pairs, circuits);
        timer.stop();
        System.out.println("Part1: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
        
        
        circuits = buildCircuits(points);
        timer.start();
        result = part2(pairs, circuits);
        timer.stop();
        System.out.println("Part2: " + result);
        System.out.println(timer.getFormatted());
        System.out.println();
    }

    private static long part1(List<Pair> pairs, Set<Circuit> circuits) {
        for (int i = 0; i < 1000; i++) {
            Pair pair = pairs.get(i);

            Circuit c1 = findCircuit(pair.p1, circuits);
            Circuit c2 = findCircuit(pair.p2, circuits);

            if (c1 == c2) {
                continue;
            }

            circuits.remove(c2);
            assert c1 != null;
            assert c2 != null;
            c1.addAll(c2);
//            circuits.stream().sorted((a, b) -> Integer.compare(b.size(), a.size())).forEach(points -> System.out.println(points));
//            System.out.println();
        }
       
        return circuits.stream()
                .sorted((a, b) -> Integer.compare(b.size(), a.size()))
                .limit(3).mapToInt(Circuit::size)
                .reduce((left, right) -> left * right).getAsInt();
    }

    private static long part2(List<Pair> pairs, Set<Circuit> circuits) {
        long result = 0;
        for (int i = 0; i < pairs.size(); i++) {
            if (circuits.size() == 1) {
                return result;
            }
            Pair pair = pairs.get(i);

            Circuit c1 = findCircuit(pair.p1, circuits);
            Circuit c2 = findCircuit(pair.p2, circuits);

            if (c1 == c2) {
                continue;
            }
            
            circuits.remove(c2);
            assert c1 != null;
            assert c2 != null;
            c1.addAll(c2);
            
            result = (long) pair.p1.x * pair.p2.x;
//            circuits.stream().sorted((a, b) -> Integer.compare(b.size(), a.size())).forEach(points -> System.out.println(points));
//            System.out.println();
        }
        return  -1;
    }

    private static List<Pair> buildPairs(List<Point> points) {
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                pairs.add(new Pair(points.get(i), points.get(j)));
            }
        }

        pairs.sort(Comparator.comparingDouble(Pair::distance));
        return pairs;
    }

    private static Set<Circuit> buildCircuits(List<Point> points) {
        Set<Circuit> circuits = new HashSet<>();
        for (int i = 0; i < points.size(); i++) {
            circuits.add(new Circuit(i, points.get(i)));
        }
        return circuits;
    }

    private static Circuit findCircuit(Point point, Set<Circuit> circuits) {
        for (Circuit circuit : circuits) {
            if (circuit.contains(point)) {
                return circuit;
            }
        }
        return null;
    }

    private static List<Point> parsePoints(List<String> parts) {
        List<Point> points = new ArrayList<>();

        for (String part : parts) {
            int[] coordinates = Arrays.stream(part.split(",")).mapToInt(Integer::parseInt).toArray();
            points.add(new Point(coordinates[0], coordinates[1], coordinates[2]));
        }
        return points;
    }




}
