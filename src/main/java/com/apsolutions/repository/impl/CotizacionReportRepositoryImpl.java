package com.apsolutions.repository.impl;

import com.apsolutions.dto.report.CotizacionReportDto;
import com.apsolutions.repository.custom.CotizacionReportRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CotizacionReportRepositoryImpl implements CotizacionReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CotizacionReportDto> filter(Date fec1, Date fec2, int idCliente) {
        StringBuilder jpql = new StringBuilder("SELECT new com.apsolutions.dto.report.CotizacionReportDto(c.id, c.fecha, p.documento, p.nombre, " +
                "CASE WHEN c.estado = 1 THEN 'REGISTRADO' WHEN c.estado = 2 THEN 'ANULADO' ELSE 'PROCESADO' END, " +
                "CASE WHEN c.origen = 1 THEN 'SISTEMA' ELSE 'WEB' END) " +
                "FROM Cotizacion c LEFT JOIN c.cliente cl LEFT JOIN cl.persona p WHERE c.fecha BETWEEN :fec1 AND :fec2 ");

        if (idCliente > 0) {
            jpql.append(" AND c.cliente.id = :idCliente ");
        }

        TypedQuery<CotizacionReportDto> query = entityManager.createQuery(jpql.toString(), CotizacionReportDto.class);

        query.setParameter("fec1", fec1);
        query.setParameter("fec2", fec2);

        if (idCliente > 0) {
            query.setParameter("idCliente", idCliente);
        }

        return query.getResultList();
    }
}
