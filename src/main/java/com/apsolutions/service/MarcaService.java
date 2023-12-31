package com.apsolutions.service;

import com.apsolutions.dto.MarcaDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.MarcaMapper;
import com.apsolutions.model.Marca;
import com.apsolutions.repository.MarcaRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.FileStorage;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;
    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private MarcaMapper marcaMapper;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public ApiResponse<String> save(MarcaDto marcaDto) {
        checkValidations(marcaDto);

        String filenameImage = "";

        if (marcaDto.getId() == null) {
            marcaDto.setId(0);
            filenameImage = marcaRepository.getImage(marcaDto.getId());
            if (marcaDto.getFile() == null) {
                fileStorage.delete(filenameImage, Global.DIR_BRANDS);
            }
        }

        marcaDto.setEstado(true);

        Marca marcaTmp = marcaMapper.toEntity(marcaDto);
        marcaTmp.setImagen(fileStorage.upload(marcaDto.getFile(), Global.DIR_BRANDS, filenameImage));
        marcaTmp.setMostrardestacado(marcaDto.getMostrardestacado());
        marcaTmp.setMostrarweb(marcaDto.getMostrarweb());
        marcaRepository.save(marcaTmp);

        return new ApiResponse<>(true, marcaTmp.getId() > 0 ? Global.SUCCESSFUL_UPDATE_MESSAGE : Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    private void checkValidations(MarcaDto marcaDto) {
        Optional<Marca> optionalMarca = marcaRepository.existsByName(marcaDto.getNombre(), marcaDto.getId());
        if (optionalMarca.isPresent()) {
            throw new CsException("La marca " + marcaDto.getNombre() + " ya se encuentra registrada");
        }
    }

    public ApiResponse<List<Marca>> list() {
        return new ApiResponse<>(true, "OK", marcaRepository.list());
    }

    public ApiResponse<Marca> read(Integer id) {
        return new ApiResponse<>(true, "OK", marcaRepository.findById(id).orElse(null));
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        if (!marcaRepository.existsById(id)) {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        marcaRepository.updateStatus(false, id);

        return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
    }
}
