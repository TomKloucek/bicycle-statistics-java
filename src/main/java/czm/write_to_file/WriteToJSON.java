package czm.write_to_file;

import czm.statistics_type.AStatistics;
import czm.helpers.Helpers;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteToJSON {
    private WriteToJSON() {}
    static Logger logger = Logger.getLogger(WriteToJSON.class.getName());

    public static boolean writeToFile(List<AStatistics> statistics) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("json_report.json"))) {
            JSONObject json = new JSONObject();
            json.put("created", Helpers.getCurrentTime());

            JSONArray array = new JSONArray();

            for (AStatistics stats : statistics) {
                JSONObject stat = new JSONObject();
                stat.put("name", stats.getName());
                stat.put("value", stats.getValue());
                array.put(stat);
            }

            json.put("statistics", array);

            writer.write(json.toString());
            return true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }
}
