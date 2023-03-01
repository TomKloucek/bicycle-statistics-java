package czm.factory;

import czm.statistics_type.AStatistics;
import czm.enums.EStatisticsType;
import czm.exceptions.StatisticsTypeNotFoundException;
import czm.statistics_type.DayWithMaximalPassages;
import czm.statistics_type.MeanCyclistsPerDay;
import czm.statistics_type.MostFavoritePartOfRoute;
import czm.statistics_type.MostFavoriteTimeOfDay;

public class StatisticsFactory {
    private StatisticsFactory() {
    }

    public static AStatistics createStatistic(EStatisticsType type, String val) throws StatisticsTypeNotFoundException {
        switch (type) {
            case DM -> {
                return new DayWithMaximalPassages(val);
            }
            case MD -> {
                return new MeanCyclistsPerDay(val);
            }
            case FP -> {
                return new MostFavoritePartOfRoute(val);
            }
            case FT -> {
                return new MostFavoriteTimeOfDay(val);
            }
            default -> throw new StatisticsTypeNotFoundException("Statistics type was not found");
        }
    }
}
