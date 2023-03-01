package czm.calculate_decorator;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;
import czm.statistics_type.DayWithMaximalPassages;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayWithMaximalPassagesDecorator extends HandlerDecorator {
    private final HashMap<LocalDate, Integer> daysWithPassages = new HashMap<>();

    public DayWithMaximalPassagesDecorator(RecordHandler wrappee) {
        super(wrappee);
    }

    @Override
    public void handleData(RowDataHolder data) {
        if (daysWithPassages.computeIfPresent(data.getFrom().toLocalDate(), (k, v) -> v + data.getNumberOfCyclists()) == null) {
            daysWithPassages.put(data.getFrom().toLocalDate(), data.getNumberOfCyclists());
        }
        super.handleData(data);
    }

    public LocalDate getDayWithMaximalPassage() {
        return daysWithPassages.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AStatistics> getStatistics(List<AStatistics> statistics) {
        statistics.add(new DayWithMaximalPassages(getDayWithMaximalPassage().toString()));
        return super.getStatistics(statistics);
    }
}
