package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
    public static List<String> readFile() {
        Path path = Paths.get("src/dao/input.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            return lines;
        } catch (IOException ex) {
            // handle exception
            System.out.println("Invalid Input file exception: " + ex);
        }
        return null;
    }
}

