package com.example.mycassandraapi.repository;

import com.example.mycassandraapi.model.MyTableModel;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface MyTableRepository extends CassandraRepository<MyTableModel, UUID> {
}
