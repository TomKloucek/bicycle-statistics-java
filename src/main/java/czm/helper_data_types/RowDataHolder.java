package czm.helper_data_types;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RowDataHolder {
    String cameraCode;
    String nextCameraCode;
    LocalDateTime from;
    LocalDateTime to;
    Integer numberOfCyclists;

    public RowDataHolder(String cameraCode, String nextCameraCode, String from, String to, String numberOfCyclists) {
        this.cameraCode = cameraCode;
        this.nextCameraCode = nextCameraCode;
        this.from = Instant.parse(from).atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.to = Instant.parse(to).atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.numberOfCyclists = Integer.valueOf(numberOfCyclists);
    }

    public String getCameraCode() {
        return cameraCode;
    }

    public String getNextCameraCode() {
        return nextCameraCode;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public Integer getNumberOfCyclists() {
        return numberOfCyclists;
    }
}
