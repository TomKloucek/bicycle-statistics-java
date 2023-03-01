package czm.file_operations;

import czm.statistics_type.AStatistics;
import czm.enums.EReportTypes;
import czm.write_to_file.WriteToCSV;
import czm.write_to_file.WriteToJSON;
import czm.write_to_file.WriteToXML;

import java.util.List;

public class WriteToFile {
    private final List<EReportTypes> reportTypes;
    private final List<AStatistics> statistics;

    public WriteToFile(List<EReportTypes> reportTypes, List<AStatistics> statistics) {
        this.reportTypes = reportTypes;
        this.statistics = statistics;
    }

    public boolean writeToFile() {
        for (EReportTypes type : reportTypes) {
            switch (type) {
                case CSV -> {
                    if (!WriteToCSV.writeToFile(statistics)) {
                        return false;
                    }
                }
                case JSON -> {
                    if (!WriteToJSON.writeToFile(statistics)) {
                        return false;
                    }
                }
                case XML -> {
                    if (!WriteToXML.writeToFile(statistics)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
