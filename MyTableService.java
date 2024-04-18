package com.example.mycassandraapi.service;

import com.example.mycassandraapi.model.MyTableModel;
import com.example.mycassandraapi.repository.MyTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MyTableService {

    @Autowired
    private MyTableRepository repository;

    public List<MyTableModel> getAllData() {
        return repository.findAll();
    }

    public MyTableModel getDataById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public MyTableModel saveData(MyTableModel model) {
        return repository.save(model);
    }

    public void deleteData(UUID id) {
        repository.deleteById(id);
    }
}
