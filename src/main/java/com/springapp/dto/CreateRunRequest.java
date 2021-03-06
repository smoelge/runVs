package com.springapp.dto;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by franschl on 16.03.15.
 */

/* The client wants to create a run.
The information he gives:
    - gpx-file
    - distance
    - groupIDs the run will be submitted to
    - duration
    - actualDistance
    - timestamp
 */

public class CreateRunRequest {
    /*@NotNull(message = "GPX-file cannot be empty.")
    File gpxFile;*/
    // TODO make sure distance matches group
    @Pattern(regexp = "2000|5000|8000|10000|15000|20000", message = "Distances allowed are 2000, 5000, 8000, 10000, 15000 or 20000.")
    String distance;
    @NotNull(message = "Duration cannot be emtpy.")
    @Min(value = 0, message = "Duration must be a positive number.")
    long duration;
    @NotNull(message = "Score cannot be null.")
    @DecimalMin(value = "0.0", message = "Score must be a positive number.")
    double actualDistance;
    ArrayList<Integer> groupIDs = new ArrayList<>();

    public ArrayList<Integer> getGroupIDs() {
        return groupIDs;
    }

    public void setGroupIDs(ArrayList<Integer> groupIDs) {
        this.groupIDs = groupIDs;
    }

    /*public File getGpxFile() {
        return gpxFile;
    }

    public void setGpxFile(File gpxFile) {
        this.gpxFile = gpxFile;
    }

    public String getBase64GpxFile() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(gpxFile);
        return new String(Base64.encodeBase64(baos.toByteArray()));
    }

    // Help from http://www.java2s.com/Code/Java/File-Input-Output/Convertobjecttobytearrayandconvertbytearraytoobject.htm
    public void setBase64GpxFile(String base64Str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decodeBase64(base64Str));
        ObjectInputStream ois = new ObjectInputStream(bais);
        gpxFile = (File) ois.readObject();
    }*/

    public Integer getDistance() {
        return Integer.parseInt(distance);
    }

    public void setDistance(Integer distance) {
        this.distance = distance.toString();
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getActualDistance() {
        return actualDistance;
    }

    public void setActualDistance(double actualDistance) {
        this.actualDistance = actualDistance;
    }
}
