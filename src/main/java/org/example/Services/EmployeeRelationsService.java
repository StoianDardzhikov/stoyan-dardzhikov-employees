package org.example.Services;

import org.example.Models.CompositeKey;
import org.example.Models.EmployeeRelation;
import org.example.Models.WorkRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeRelationsService {

    public EmployeeRelation getLongestEmployeeRelation(BufferedReader data) throws IOException {
        Map<Integer, List<Integer>> projectIdToEmployeeIds = new HashMap<>();
        Map<CompositeKey, EmployeeRelation> employeeRelations = new HashMap<>();
        Map<CompositeKey, WorkRecord> workRecords = new HashMap<>();

        EmployeeRelation maxWorkDaysRelation = null;

        String line = data.readLine();
        while (line != null) {
            String[] tokens = line.split(",");
            WorkRecord workRecord = new WorkRecord(tokens[0], tokens[1], tokens[2], tokens[3]);
            int employeeId = workRecord.getEmployeeId();
            int projectId = workRecord.getProjectId();
            CompositeKey workRecordKey = new CompositeKey(employeeId, projectId);

            workRecords.put(workRecordKey, workRecord);
            List<Integer> otherEmployeeIds = projectIdToEmployeeIds.get(projectId);
            if (otherEmployeeIds != null) {
                for (Integer employeeInProject : otherEmployeeIds) {
                    CompositeKey relationKey = new CompositeKey(workRecord.getEmployeeId(), employeeInProject);
                    EmployeeRelation value = employeeRelations.get(relationKey);
                    if (value == null) {
                        value = new EmployeeRelation(employeeId, employeeInProject);
                        employeeRelations.put(relationKey, value);
                    }

                    CompositeKey otherEmployeeWorkRecordKey = new CompositeKey(employeeInProject, projectId);
                    WorkRecord otherEmployeeWorkRecord = workRecords.get(otherEmployeeWorkRecordKey);
                    if (otherEmployeeWorkRecord == null)
                        continue;

                    long daysWorkedTogether = workRecord.overlappingDays(otherEmployeeWorkRecord);
                    value.addWorkedDays(projectId, daysWorkedTogether);

                    if (maxWorkDaysRelation == null)
                        maxWorkDaysRelation = value;
                    else if (maxWorkDaysRelation != value && maxWorkDaysRelation.getAllWorkedDays() < value.getAllWorkedDays())
                        maxWorkDaysRelation = value;

                    employeeRelations.put(relationKey, value);
                }
                otherEmployeeIds.add(employeeId);
                projectIdToEmployeeIds.put(projectId, otherEmployeeIds);
            } else {
                List<Integer> projectEmployees = new ArrayList<>();
                projectEmployees.add(employeeId);
                projectIdToEmployeeIds.put(projectId, projectEmployees);
            }
            line = data.readLine();
        }

        return maxWorkDaysRelation;
    }
}
