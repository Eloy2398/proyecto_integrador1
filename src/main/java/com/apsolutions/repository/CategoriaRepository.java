package com.apsolutions.repository;

import com.apsolutions.dto.website.CategoriaWebsiteDto;
import com.apsolutions.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c WHERE c.estado = true AND c.nombre = :nombre AND c.id <> :id")
    Optional<Categoria> existsByName(String nombre, Integer id);

    @Query("SELECT c FROM Categoria c WHERE c.estado = true")
    List<Categoria> list();

    @Modifying
    @Query("UPDATE Categoria c SET c.estado = :estado WHERE c.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT c.imagen FROM Categoria c WHERE c.id = :id")
    String getImage(Integer id);

    @Query("SELECT c.nombre FROM Categoria c WHERE c.id = :id")
    String getName(Integer id);

    @Query("SELECT new com.apsolutions.dto.website.CategoriaWebsiteDto(c.id, c.nombre, c.nombreUrl, c.imagen) FROM Categoria c WHERE c.estado = true AND c.mostrarweb = 1 AND c.mostrardestacado = 1")
    List<CategoriaWebsiteDto> getCategoriesMain();

    @Query("SELECT new com.apsolutions.dto.website.CategoriaWebsiteDto(c.id, c.nombre, c.nombreUrl) FROM Categoria c WHERE c.estado = true AND c.mostrarweb = 1")
    List<CategoriaWebsiteDto> getCategories();

    @Query("SELECT c FROM Categoria c WHERE c.estado = true AND c.mostrarweb = 1 AND c.id = :id AND c.nombreUrl LIKE :urlName")
    Optional<Categoria> validateByIdAndName(Integer id, String urlName);
}
