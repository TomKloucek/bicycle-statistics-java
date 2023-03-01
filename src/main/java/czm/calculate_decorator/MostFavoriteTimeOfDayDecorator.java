package czm.calculate_decorator;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;
import czm.statistics_type.MostFavoriteTimeOfDay;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFavoriteTimeOfDayDecorator extends HandlerDecorator {
    public MostFavoriteTimeOfDayDecorator(RecordHandler wrappee) {
        super(wrappee);
    }

    private final HashMap<String, Integer> partOfDay = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void handleData(RowDataHolder data) {
        if (partOfDay.computeIfPresent(data.getFrom().format(formatter) + "-" + data.getTo().format(formatter), (k, v) -> v + data.getNumberOfCyclists()) == null) {
            partOfDay.put(data.getFrom().format(formatter) + "-" + data.getTo().format(formatter), data.getNumberOfCyclists());
        }
        super.handleData(data);
    }

    public String getMostFavoriteTimeOfDay() {
        return partOfDay.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AStatistics> getStatistics(List<AStatistics> statistics) {
        statistics.add(new MostFavoriteTimeOfDay(getMostFavoriteTimeOfDay()));
        return super.getStatistics(statistics);
    }
}
