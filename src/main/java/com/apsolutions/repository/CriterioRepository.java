package com.apsolutions.repository;

import com.apsolutions.model.Categoria;
import com.apsolutions.model.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriterioRepository extends JpaRepository<Criterio, Integer> {

}
