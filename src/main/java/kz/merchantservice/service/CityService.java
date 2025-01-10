package kz.merchantservice.service;

import jakarta.validation.Valid;
import kz.merchantservice.CityMapper;
import kz.merchantservice.exception.CustomException;
import kz.merchantservice.model.dto.CityDTO;
import kz.merchantservice.model.entity.CityEntity;
import kz.merchantservice.repository.CityRepository;
import kz.merchantservice.utils.ErrorMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityService {

    private final CityRepository repository;
    private final CityMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public CityDTO saveOne(CityDTO dto) throws CustomException {
        dto.setName(validateName(dto.getName()));
        CityEntity entity = mapper.toEntity(dto);
        repository.save(entity);

        return mapper.toDTO(entity);
    }

    public CityDTO getOne(UUID id) throws CustomException {
        return mapper.toDTO(findEntityById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public CityDTO updateOne(UUID id, @Valid CityDTO dto) throws CustomException {
        dto.setName(validateName(id, dto.getName()));
        CityEntity entity = mapper.toEntity(dto);
        repository.save(entity);

        return mapper.toDTO(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteOne(UUID id) throws CustomException {
        repository.delete(findEntityById(id));
    }

    private String validateName(UUID id, String name) throws CustomException {
        name = name.trim();
        CityEntity entity = findEntityById(id);

        if (!id.equals(entity.getId()) && name.equals(entity.getName())) {
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(ErrorMessageSource.CITY_ALREADY_EXISTS.getText(name))
                    .build();
        }

        return name;
    }

    private String validateName(String name) throws CustomException {
        name = name.trim();

        if (repository.existsByNameIgnoreCase(name)) {
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(ErrorMessageSource.CITY_ALREADY_EXISTS.getText(name))
                    .build();
        }

        return name;
    }

    private CityEntity findEntityById(UUID id) throws CustomException {
        return repository.findById(id).orElseThrow(
                () -> CustomException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(ErrorMessageSource.CITY_NOT_FOUND.getText(id.toString()))
                        .build()
        );
    }
}
