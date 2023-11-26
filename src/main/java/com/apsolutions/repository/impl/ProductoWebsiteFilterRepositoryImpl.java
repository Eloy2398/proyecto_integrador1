package com.apsolutions.repository.impl;

import com.apsolutions.dto.website.ProductoWebsiteDto;
import com.apsolutions.repository.custom.ProductoWebsiteFilterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class ProductoWebsiteFilterRepositoryImpl implements ProductoWebsiteFilterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductoWebsiteDto> getAll(int idCategory, String idBrands, String strPriceRange, int sortBy, int page) {
        StringBuilder jpql = new StringBuilder("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.nombre, p.nombreUrl, m.nombre, p.precio, p.imagen) FROM Producto p " +
                "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1 AND c.id = :idCategory");

        double[] priceRangeValues = priceRangeValues(strPriceRange);
        jpql.append(getJpqlFiltersAppend(idBrands, priceRangeValues));

        if (sortBy == 1) {
            jpql.append(" ORDER BY p.precio ASC");
        } else if (sortBy == 2) {
            jpql.append(" ORDER BY p.precio DESC");
        } else {
            jpql.append(" ORDER BY p.id DESC");
        }

        TypedQuery<ProductoWebsiteDto> query = entityManager.createQuery(jpql.toString(), ProductoWebsiteDto.class);

        query.setParameter("idCategory", idCategory);

        if (priceRangeValues[0] > 0) {
            query.setParameter("minPrice", priceRangeValues[0]);
        }

        if (priceRangeValues[1] > 0) {
            query.setParameter("maxPrice", priceRangeValues[1]);
        }

        int maxResults = 12;
        page = (page - 1) * maxResults;

        return query.setFirstResult(page).setMaxResults(maxResults).getResultList();
    }

    @Override
    public Long getTotalRecords(int idCategory, String idBrands, String strPriceRange) {
        StringBuilder jpql = new StringBuilder("SELECT COUNT(p.id) FROM Producto p INNER JOIN p.categoria c INNER JOIN p.marca m WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1 AND c.id = :idCategory");

        double[] priceRangeValues = priceRangeValues(strPriceRange);
        jpql.append(getJpqlFiltersAppend(idBrands, priceRangeValues));

        TypedQuery<Long> query = entityManager.createQuery(jpql.toString(), Long.class);

        query.setParameter("idCategory", idCategory);

        if (priceRangeValues[0] > 0) {
            query.setParameter("minPrice", priceRangeValues[0]);
        }

        if (priceRangeValues[1] > 0) {
            query.setParameter("maxPrice", priceRangeValues[1]);
        }

        return query.getSingleResult();
    }

    private String getJpqlFiltersAppend(String idBrands, double[] priceRangeValues) {
        String jpql = "";
        if (StringUtils.hasText(idBrands)) {
            jpql += " AND m.id IN (" + idBrands.replaceAll(":", ",") + ")";
        }

        if (priceRangeValues[0] > 0) {
            jpql += " AND p.precio >= :minPrice";
        }

        if (priceRangeValues[1] > 0) {
            jpql += " AND p.precio <= :maxPrice";
        }

        return jpql;
    }

    private double[] priceRangeValues(String strPriceRange) {
        if (StringUtils.hasText(strPriceRange)) {
            String[] priceRange = strPriceRange.split("-");
            return new double[]{Double.parseDouble(priceRange[0]), Double.parseDouble(priceRange[1])};
        }

        return new double[]{0, 0};
    }
}
