package czm.calculate_decorator;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;

import java.util.List;

public class HandlerDecorator implements RecordHandler {
    protected RecordHandler wrappee;

    public HandlerDecorator(RecordHandler wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void handleData(RowDataHolder data) {
        wrappee.handleData(data);
    }

    @Override
    public List<AStatistics> getStatistics(List<AStatistics> statistics) {
        return wrappee.getStatistics(statistics);
    }
}
