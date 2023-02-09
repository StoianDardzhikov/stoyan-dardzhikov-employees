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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, Long> projectWorkDaysEntry : projectWorkedDays.entrySet()) {
            int projectId = projectWorkDaysEntry.getKey();
            long days = projectWorkDaysEntry.getValue();
            stringBuilder.append(firstEmployeeId).append(", ").append(secondEmployeeId).append(", ").append(projectId).append(", ").append(days).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}