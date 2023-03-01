package czm.write_to_file;

import czm.statistics_type.AStatistics;
import czm.helpers.Helpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteToCSV {
    private WriteToCSV() {
    }

    static Logger logger = Logger.getLogger(WriteToCSV.class.getName());

    public static boolean writeToFile(List<AStatistics> statistics) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("csv_report.csv"))) {
            writer.write("name,value,created\n");
            for (var stat : statistics) {
                writer.write(stat.getName()+","+stat.getValue()+","+ Helpers.getCurrentTime()+"\n");
            }
            return true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }
}
