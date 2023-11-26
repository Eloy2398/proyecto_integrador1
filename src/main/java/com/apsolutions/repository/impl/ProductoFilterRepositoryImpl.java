package com.apsolutions.repository.impl;

import com.apsolutions.dto.ProductoListDto;
import com.apsolutions.repository.custom.ProductoFilterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoFilterRepositoryImpl implements ProductoFilterRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductoListDto> filter(int tipo, String nombre, int idCategory, int idBrand) {
        StringBuilder jpql = new StringBuilder("SELECT new com.apsolutions.dto.ProductoListDto(p.id, p.codigo, p.nombre, p.descripcion, c.nombre, m.nombre, p.precio, p.stock) " +
                "FROM Producto p INNER JOIN p.categoria c INNER JOIN p.marca m WHERE p.estado = true ");

        if (!nombre.isEmpty()) {
            if (tipo == 1) {
                jpql.append(" AND p.codigo = :nombre");
            } else {
                jpql.append(" AND p.nombre = :nombre");
            }
        }

        if (idCategory > 0) {
            jpql.append(" AND p.categoria.id = :idCategory");
        }

        if (idBrand > 0) {
            jpql.append(" AND p.marca.id = :idBrand");
        }

        TypedQuery<ProductoListDto> query = entityManager.createQuery(jpql.toString(), ProductoListDto.class);

        if (!nombre.isEmpty()) {
            query.setParameter("nombre", nombre);
        }

        if (idCategory > 0) {
            query.setParameter("idCategory", idCategory);
        }

        if (idBrand > 0) {
            query.setParameter("idBrand", idBrand);
        }

        return query.getResultList();
    }
}
