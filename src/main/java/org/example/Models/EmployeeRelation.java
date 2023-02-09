package org.example.Models;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRelation {
    private final int firstEmployeeId;
    private final int secondEmployeeId;
    private final Map<Integer, Long> projectWorkedDays = new HashMap<>();
    private int allWorkedDays = 0;

    public EmployeeRelation(int firstEmployeeId, int secondEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
    }

    public void addWorkedDays(int project, long workedDays) {
        allWorkedDays += workedDays;
        projectWorkedDays.put(project, workedDays);
    }

    public int getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public int getSecondEmployeeId() {
        return secondEmployeeId;
    }

    public Map<Integer, Long> getProjectWorkedDays() {
        return projectWorkedDays;
    }

    public int getAllWorkedDays() {
        return allWorkedDays;
    }

    @Override
    public int hashCode() {
        return firstEmployeeId * secondEmployeeId + firstEmployeeId + secondEmployeeId;
    }
}