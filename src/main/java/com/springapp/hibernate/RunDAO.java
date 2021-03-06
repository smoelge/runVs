package com.springapp.hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by franschl on 31.01.15.
 */
@Entity
@Table(name = "runs", schema = "", catalog = "Ghostrunner")
public class RunDAO {
    private int runID;
    private int distance;
    private long duration;
    private double actualDistance;
    private Timestamp timestamp;
    private Set<GroupDAO> groups;
    private UserDAO user;

    @Id
    @Column(name = "id")
    public int getRunID() {
        return runID;
    }

    public void setRunID(int id) {
        this.runID = id;
    }

    @Basic
    @Column(name = "distance")
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name = "duration")
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "score")
    public double getActualDistance() {
        return actualDistance;
    }

    public void setActualDistance(double score) {
        this.actualDistance = score;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunDAO that = (RunDAO) o;

        if (distance != that.distance) return false;
        if (Double.compare(that.duration, duration) != 0) return false;
        if (runID != that.runID) return false;
        if (actualDistance != that.actualDistance) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = runID;
        result = 31 * result + distance;
        temp = Double.doubleToLongBits(duration);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) actualDistance;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

 //many to many in XML
    public Set<GroupDAO> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupDAO> groups) {
        this.groups = groups;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }
}
