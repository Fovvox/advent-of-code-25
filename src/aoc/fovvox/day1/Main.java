package aoc.fovvox.day1;

import aoc.fovvox.Util;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final int MIN = 0;
    private static final int MAX = 99;
    private static final int START_POS = 50;


    static void main() throws IOException {
        List<String> input = Util.parseLines("inputs/day1.txt");

        int answer = part1(input);
        System.out.println(answer);
    }

    private static int part1(List<String> input) {
        int zeroCounter = 0;

        int pos = START_POS;


        for (String move : input) {
            if (move.isEmpty()) {
                continue;
            }
            System.out.print(move);
            String direction = move.substring(0, 1);
            int distance = Integer.parseInt(move.substring(1)) % (MAX + 1);
            if (direction.equals("L")) {
                System.out.print("(-):");
                pos -= distance;
            }
            if (direction.equals("R")) {
                System.out.print("(+):");
                pos += distance;
            }
            System.out.print(pos + " -> ");
            pos = normalize(pos);
            System.out.println(pos);
            if (pos == 0) {
                zeroCounter++;
            }


        }




        return zeroCounter;
    }

    private static int normalize(int pos) {
        if (pos > MAX) {
            return pos % (MAX + 1);
        }
        if (pos < MIN) {
            return (MAX + 1) + pos;
        }

        return pos;
    }
}
