package czm;

import czm.helper_data_types.Arguments;
import czm.file_operations.ReadFile;
import czm.file_operations.WriteToFile;
import czm.statistics_type.AStatistics;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final int NUMBER_OF_ARGUMENTS = 3;

    public static void main(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            logger.log(Level.SEVERE,"Number of arguments is not "+NUMBER_OF_ARGUMENTS);
            System.exit(1);
        }
        Arguments arguments = new Arguments(args);
        ReadFile readFile = new ReadFile(arguments.getStatisticsType(),arguments.getInputFile());
        List<AStatistics> statisticsList = readFile.getStatisticsFromFile();
        WriteToFile write = new WriteToFile(arguments.getReportTypes(), statisticsList);
        boolean result = write.writeToFile();
        if (result) {
            logger.log(Level.FINEST,"Reports were written to files");
            System.exit(0);
        }
    }

}