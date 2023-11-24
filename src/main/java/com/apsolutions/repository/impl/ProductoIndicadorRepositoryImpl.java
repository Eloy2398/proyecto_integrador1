package com.apsolutions.repository.impl;

import com.apsolutions.dto.indicator.ProductoIndicatorDto;
import com.apsolutions.repository.custom.ProductoIndicadorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoIndicadorRepositoryImpl implements ProductoIndicadorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductoIndicatorDto> getStockListForChart(int year, int idCategory, int sortByStock) {
        StringBuilder jpql = new StringBuilder("SELECT new com.apsolutions.dto.indicator.ProductoIndicatorDto(p.nombre, p.stock, SUM(CASE WHEN m.tipo = 1 THEN md.cantidad ELSE 0 END) AS inc, SUM(CASE WHEN m.tipo = 2 THEN md.cantidad ELSE 0 END) AS out) FROM Movimientodetalle md " +
                "LEFT JOIN md.movimiento m LEFT JOIN md.producto p " +
                "WHERE m.estado = true AND p.estado = true AND YEAR(m.fecha) = :year");

        if (idCategory > 0) {
            jpql.append(" AND p.categoria.id = :idCategory");
        }

        jpql.append(" GROUP BY p.id");

        if (sortByStock == 1) {
            jpql.append(" ORDER BY p.stock ASC");
        } else {
            jpql.append(" ORDER BY p.stock DESC");
        }

        jpql.append(" LIMIT 5");

        TypedQuery<ProductoIndicatorDto> query = entityManager.createQuery(jpql.toString(), ProductoIndicatorDto.class);

        query.setParameter("year", year);

        if (idCategory > 0) {
            query.setParameter("idCategory", idCategory);
        }

        return query.getResultList();
    }
}
