package com.services;

import com.dao.DevisRepository;

import com.dao.DevisRepositoryImpl;
import com.models.Devis;

import java.sql.SQLException;

public class DevisService {
    private final DevisRepositoryImpl devisRepository;

    public DevisService() throws SQLException {
        this.devisRepository = new DevisRepositoryImpl();
    }
    public void createDevis(Devis newDevis) throws SQLException {
        devisRepository.createDevis(newDevis);
    }
}
