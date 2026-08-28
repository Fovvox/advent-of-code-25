package aoc.fovvox.day10;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import aoc.fovvox.ExecutionTimer;
import aoc.fovvox.Util;

public class Main {
    static void main() throws IOException {
        final List<String> parts = Util.parseLines("inputs/day10.moc.txt");
        final List<Machine> machines = parts.stream().map(Machine::parse).toList();
        final ExecutionTimer executionTimer = new ExecutionTimer();
        executionTimer.start();
        final long result1 = part1(machines);
        executionTimer.stop();
        System.out.println("Part 1: " + result1);
        System.out.println(executionTimer.getFormatted());
        executionTimer.start();
        final long result2 = part2(machines);
        executionTimer.stop();
        System.out.println("Part 2: " + result2);
        System.out.println(executionTimer.getFormatted());
    }

    private static long part1(final List<Machine> machines) {
        int sum = 0;
        for (final Machine machine : machines) {
            final int steps = machine.enable();
//            System.out.println(machine + " -> " + steps);
            sum += steps;
        }
        return sum;
    }

    private static long part2(final List<Machine> machines) {
        int sum = 0;
        for (final Machine machine : machines) {
            final int steps = machine.configureJoltage();
                        System.out.println(machine + " -> " + steps);
            sum += steps;
        }
        return sum;
    }
}
