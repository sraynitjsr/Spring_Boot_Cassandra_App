package com.example.mycassandraapi.controller;

import com.example.mycassandraapi.model.MyTableModel;
import com.example.mycassandraapi.service.MyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/my_table")
public class MyTableController {

    @Autowired
    private MyTableService service;

    @GetMapping
    public List<MyTableModel> getAllData() {
        return service.getAllData();
    }

    @GetMapping("/{id}")
    public MyTableModel getDataById(@PathVariable UUID id) {
        return service.getDataById(id);
    }

    @PostMapping
    public MyTableModel saveData(@RequestBody MyTableModel model) {
        return service.saveData(model);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable UUID id) {
        service.deleteData(id);
    }
}
