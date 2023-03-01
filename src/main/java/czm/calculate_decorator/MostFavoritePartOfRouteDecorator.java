package czm.calculate_decorator;

import czm.statistics_type.AStatistics;
import czm.helper_data_types.RowDataHolder;
import czm.statistics_type.MostFavoritePartOfRoute;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFavoritePartOfRouteDecorator extends HandlerDecorator {
    private final HashMap<String, Integer> partOfRoute = new HashMap<>();

    public MostFavoritePartOfRouteDecorator(RecordHandler wrappee) {
        super(wrappee);
    }

    @Override
    public void handleData(RowDataHolder data) {
        if (partOfRoute.computeIfPresent(data.getCameraCode() + "-" + data.getNextCameraCode(), (k, v) -> v + data.getNumberOfCyclists()) == null) {
            partOfRoute.put(data.getCameraCode() + "-" + data.getNextCameraCode(), data.getNumberOfCyclists());
        }
        super.handleData(data);
    }

    public String getMostFavoritePartOfRoute() {
        return partOfRoute.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AStatistics> getStatistics(List<AStatistics> statistics) {
        statistics.add(new MostFavoritePartOfRoute(getMostFavoritePartOfRoute()));
        return super.getStatistics(statistics);
    }
}
