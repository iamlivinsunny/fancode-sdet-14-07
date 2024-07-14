package org.assignment.utlities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtilities {
    public static void writeResponseToFile(String filePath, String fileContent){
        Path path = Paths.get(filePath);
        try {
            Files.writeString(path, fileContent,
                    StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
