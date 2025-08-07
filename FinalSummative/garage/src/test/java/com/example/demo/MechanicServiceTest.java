package com.example.demo;

import com.example.demo.MockRepo.InMemoryMechanicRepository;
import com.example.demo.model.Mechanic;
import com.example.demo.service.MechanicService;
import com.example.demo.service.MechanicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MechanicServiceTest {

    private MechanicService service;

    @BeforeEach
    void setUp() {
        service = new MechanicServiceImpl(new InMemoryMechanicRepository());
    }
}
