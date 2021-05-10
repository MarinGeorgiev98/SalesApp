package fileoperator;

import com.google.gson.Gson;
import model.Person;
import model.Report;

import java.io.*;

public class FileOperatorImpl implements FileOperator {

    private final Gson gsonObject;
    private final PrintWriter printWriter;

    public FileOperatorImpl(Gson gson, PrintWriter printWriter) {
        this.gsonObject = gson;
        this.printWriter = printWriter;
    }

    @Override
    public void writeToFile(String dataFilePath, String reportFilePath) throws IOException {
        String dataFile = readFilePath(dataFilePath);
        String reportFile = readFilePath(reportFilePath);

        Person[] people = gsonObject.fromJson(dataFile, Person[].class);
        Report report = gsonObject.fromJson(reportFile, Report.class);

        printWriter.println("Name  , Score");
        for (Person person : people) {
            double score;

            if (report.isUseExperienceMultiplier()) {
                score = Math.round(
                        (person.getTotalSales() /
                                (person.getSalesPeriod() * person.getExperienceMultiplier())) * 10) / 10.0;
            } else {
                score = Math.round((person.getTotalSales() / person.getSalesPeriod()) * 10) / 10.0;
            }

            if (isValid(person, report, score)) {
                printWriter.printf("%s, %.1f", person.getName(), score);
                printWriter.println();
            }
        }
        printWriter.close();
    }

    @Override
    public String readFilePath(String path) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(path))));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public boolean isValid(Person person, Report report, double score) {
        return  score >= report.getTopPerformersThreshold() &&
                person.getSalesPeriod() <= report.getPeriodLimit();
    }
}
