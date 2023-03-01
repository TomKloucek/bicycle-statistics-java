package czm.file_operations;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;
import czm.calculate_decorator.*;
import czm.enums.EStatisticsType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFile {
    Logger logger = Logger.getLogger(ReadFile.class.getName());
    private static final String COMMA_DELIMITER = ",";
    private List<EStatisticsType> stats;
    private String filename;

    public ReadFile(List<EStatisticsType> stats, String filename) {
        this.stats = stats;
        this.filename = filename;
    }

    public List<AStatistics> getStatisticsFromFile() {
        List<AStatistics> statistics = new ArrayList<>();
        HandlerDecorator handler = decorateHandler(stats, new HandlerDecorator(new StatsHandler()));
        try (Scanner scanner = new Scanner(new File(filename));) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                List<String> values = getRecordFromLine(scanner.nextLine());
                String numberOfCyclists = values.size() == 4 ? "0" : values.get(4);
                RowDataHolder row = new RowDataHolder(values.get(0), values.get(1), values.get(2), values.get(3), numberOfCyclists);
                handler.handleData(row);
            }

        } catch (FileNotFoundException f) {
            logger.log(Level.SEVERE, "File was not found - try again");
            return new ArrayList<>();
        }
        return handler.getStatistics(statistics);
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    private HandlerDecorator decorateHandler(List<EStatisticsType> stats, HandlerDecorator handler) {
        if (stats.contains(EStatisticsType.DM)) {
            handler = new DayWithMaximalPassagesDecorator(handler);
        }
        if (stats.contains(EStatisticsType.FP)) {
            handler = new MostFavoritePartOfRouteDecorator(handler);
        }
        if (stats.contains(EStatisticsType.FT)) {
            handler = new MostFavoriteTimeOfDayDecorator(handler);
        }
        if (stats.contains(EStatisticsType.MD)) {
            handler = new MeanCyclistsPerDayDecorator(handler);
        }
        return handler;
    }
}
