package com.apsolutions.repository;

import com.apsolutions.model.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterioRepository extends JpaRepository<Criterio, Integer> {

}
