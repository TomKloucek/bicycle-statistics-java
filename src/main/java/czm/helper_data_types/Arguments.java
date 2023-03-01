package czm.helper_data_types;

import czm.enums.EReportTypes;
import czm.enums.EStatisticsType;
import czm.exceptions.WrongReportTypeInputException;
import czm.exceptions.WrongStatisticTypeInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arguments {
    private final Logger logger = Logger.getLogger(Arguments.class.getName());
    private String inputFile;
    private List<EStatisticsType> statisticsType;
    private List<EReportTypes> reportTypes;

    public Arguments(String[] args) {
        handleArguments(args);
    }

    private void handleArguments(String[] args) {
        this.inputFile = args[0];
        try {
            this.statisticsType = getStatisticsType(args[1]);
            this.reportTypes = getReportTypes(args[2]);
        } catch (WrongStatisticTypeInputException | WrongReportTypeInputException e) {
            logger.log(Level.SEVERE, e.getMessage());
            System.exit(1);
        }
    }

    private List<EStatisticsType> getStatisticsType(String typesList) throws WrongStatisticTypeInputException {
        String[] values = typesList.split(",");
        List<EStatisticsType> statisticsTypes = new ArrayList<>();
        for (String value : values) {
            switch (value) {
                case "DM" -> statisticsTypes.add(EStatisticsType.DM);
                case "MD" -> statisticsTypes.add(EStatisticsType.MD);
                case "FP" -> statisticsTypes.add(EStatisticsType.FP);
                case "FT" -> statisticsTypes.add(EStatisticsType.FT);
                default ->
                        throw new WrongStatisticTypeInputException("Your input contained not known types of statistics");
            }
        }
        return statisticsTypes;
    }

    private List<EReportTypes> getReportTypes(String reportTypes) throws WrongReportTypeInputException {
        String[] values = reportTypes.split(",");
        List<EReportTypes> reportTypesList = new ArrayList<>();
        for (String value : values) {
            switch (value) {
                case "CSV" -> reportTypesList.add(EReportTypes.CSV);
                case "JSON" -> reportTypesList.add(EReportTypes.JSON);
                case "XML" -> reportTypesList.add(EReportTypes.XML);
                default -> throw new WrongReportTypeInputException("Your input contained not known types of reports");
            }
        }
        return reportTypesList;
    }

    public String getInputFile() {
        return inputFile;
    }

    public List<EStatisticsType> getStatisticsType() {
        return statisticsType;
    }

    public List<EReportTypes> getReportTypes() {
        return reportTypes;
    }
}

