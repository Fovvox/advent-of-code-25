package aoc.fovvox.day2;

import aoc.fovvox.Util;

import java.io.IOException;
import java.util.List;

public class Main {
    static void main() throws IOException {
        List<String> ranges = Util.parse("inputs/day2.txt", ",");

        long result = part2(ranges);
        System.out.println(result);

    }

    private static long part2(List<String> ranges) {
        long result = 0;

        for (String range : ranges) {
            System.out.println(range);
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long i = start; i <= end; i++) {
                String num = Long.toString(i);
                for (int j = 1; j <= num.length() / 2; j++) {
                    String part = num.substring(0, j);
                    String replaced = num.replace(part, "");
                    if (replaced.isEmpty()) {
                        result += i;
                        System.out.println("\t" + num);
                        break;
                    }
                }
            }

        }


        return result;
    }

    private static long part1(List<String> ranges) {
        long result = 0;

        for (String range : ranges) {
            System.out.println(range);
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long i = start; i <= end; i++) {
                String num = Long.toString(i);
                if (num.length() % 2 == 1) {
                    continue;
                }
                String num1 = num.substring(0, (num.length()/2));
                String num2 = num.substring(num.length()/2);

                if (num1.equals(num2)) {
                    result += i;
                    System.out.println("\t" + num);
                }
            }

        }


        return result;
    }
}
