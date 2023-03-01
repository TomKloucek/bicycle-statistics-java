package czm.calculate_decorator;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;

import java.util.List;

public interface RecordHandler {
    void handleData(RowDataHolder data);
    List<AStatistics> getStatistics(List<AStatistics> statistics);
}
