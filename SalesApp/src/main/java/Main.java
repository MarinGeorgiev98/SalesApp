import com.google.gson.Gson;
import fileoperator.FileOperator;
import fileoperator.FileOperatorImpl;
import java.io.*;
import java.util.Scanner;

import static constants.Constants.RESULT_PATH;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(new java.io.FileWriter(RESULT_PATH));
        Gson gsonObject = new Gson();

        String dataFilePath = sc.nextLine();
        String reportFilePath = sc.nextLine();

        FileOperator fileOperator = new FileOperatorImpl(gsonObject, printWriter);
        fileOperator.writeToFile(dataFilePath, reportFilePath);
    }
}