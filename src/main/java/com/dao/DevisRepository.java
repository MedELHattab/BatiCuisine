package com.dao;

import com.models.Devis;

import java.sql.SQLException;

public interface DevisRepository  {

    void createDevis(Devis devis) throws SQLException;

}
