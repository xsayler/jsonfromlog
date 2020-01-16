package com.sayler;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author sayler
 */
@UtilityClass
class FileUtils {

    private Boolean existFile(String rootPath, String fileName) {
        Path path = Objects.nonNull(rootPath) ? Paths.get(rootPath, fileName) : Paths.get(fileName);
        File file = path.toFile();
        return file.exists() && !file.isDirectory();
    }

    String calcUniqueFileName(String rootPath, String fileName, String ext) {
        int i = 0;
        String newFileName;
        do {
            String postfix = i > 0 ? String.format("(%s)", i) : "";
            newFileName = fileName + postfix + ext;
            i++;
        } while (existFile(rootPath, newFileName));
        return newFileName;
    }
}
