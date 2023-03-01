package czm.calculate_decorator;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;
import czm.statistics_type.MeanCyclistsPerDay;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MeanCyclistsPerDayDecorator extends HandlerDecorator {
    Set<LocalDate> dates = new HashSet<>();
    int numberOfCyclists = 0;

    public MeanCyclistsPerDayDecorator(RecordHandler wrappee) {
        super(wrappee);
    }

    @Override
    public void handleData(RowDataHolder data) {
        dates.add(data.getFrom().toLocalDate());
        numberOfCyclists += data.getNumberOfCyclists();
        super.handleData(data);
    }

    public double getMeanCyclistsPerDay() {
        return (double) numberOfCyclists / (double) dates.size();
    }

    @Override
    public List<AStatistics> getStatistics(List<AStatistics> statistics) {
        statistics.add(new MeanCyclistsPerDay(String.valueOf(getMeanCyclistsPerDay())));
        return super.getStatistics(statistics);
    }
}
