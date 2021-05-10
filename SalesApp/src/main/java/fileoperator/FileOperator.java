package fileoperator;

import model.Person;
import model.Report;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileOperator {
    void writeToFile(String str1, String str2) throws IOException;
    String readFilePath(String path) throws IOException;
    boolean isValid(Person person, Report report, double score);
}
