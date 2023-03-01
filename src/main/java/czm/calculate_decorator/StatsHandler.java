package czm.calculate_decorator;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;

import java.util.List;

public class StatsHandler implements RecordHandler {
    @Override
    public void handleData(RowDataHolder data) {}

    @Override
    public List<AStatistics> getStatistics(List<AStatistics> statistics) {
        return statistics;
    }
}
