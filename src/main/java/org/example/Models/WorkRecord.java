package org.example.Models;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class WorkRecord {
    private final int employeeId;
    private final int projectId;
    LocalDate dateFrom;
    LocalDate dateTo;

    public WorkRecord(String employeeId, String projectId, String dateFrom, String dateTo) {
        this.employeeId = Integer.parseInt(employeeId.trim());
        this.projectId = Integer.parseInt(projectId.trim());
        this.dateFrom = LocalDate.parse(dateFrom.trim());
        dateTo = dateTo.trim();
        this.dateTo = dateTo.equals("NULL") ? LocalDate.now() : LocalDate.parse(dateTo);
    }

    public long overlappingDays(WorkRecord other) {
        LocalDate dateFromLatest = dateFrom.compareTo(other.dateFrom) > 0 ? dateFrom : other.dateFrom;
        LocalDate dateToEarliest = dateTo.compareTo(other.dateTo) < 0 ? dateTo : other.dateTo;

        if (dateFromLatest.compareTo(dateToEarliest) > 0)
            return 0;

        return DAYS.between(dateFromLatest, dateToEarliest);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }
}
