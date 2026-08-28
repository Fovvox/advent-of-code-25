package aoc.fovvox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Util {
    public static List<String> parseLines(String file) throws IOException {
        List<String> result = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Path.of(file))) {
            lines.forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<String> parse(String file, String splitter) throws IOException {
        String content = Files.readString(Path.of(file));

        return Arrays.asList(content.split(splitter));
    }

    public static String[][] parseStringMatrix(String file) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(file));
        return lines.stream().map(s -> s.split("")).toArray(String[][]::new);
    }

    public static <T> void printMatrix(T[][] matrix) {
        for (T[] ts : matrix) {
            for (T t : ts) {
                System.out.print(t);
            }
            System.out.println();
        }
    }

    public static <T> boolean equalsList(final List<T> l1, final List<T> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if ((l1.get(i) == null || l2.get(i) == null) && l1.get(i) != l2.get(1)) {
                return false;
            }
            if (!l1.get(i).equals(l2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
