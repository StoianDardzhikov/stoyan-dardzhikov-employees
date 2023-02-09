package org.example.Controllers;

import org.example.Models.EmployeeRelation;
import org.example.Services.EmployeeRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/relations")
public class EmployeeRelationsController {

    EmployeeRelationsService employeeRelationsService;

    @Autowired
    public EmployeeRelationsController(EmployeeRelationsService employeeRelationsService) {
        this.employeeRelationsService = employeeRelationsService;
    }

    @PostMapping("/longest")
    public EmployeeRelation longest(@RequestParam("file") MultipartFile file) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        return employeeRelationsService.getLongestEmployeeRelation(bufferedReader);
    }
}