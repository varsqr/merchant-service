package kz.merchantservice;

import kz.merchantservice.model.dto.CityDTO;
import kz.merchantservice.model.entity.CityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDTO toDTO(CityEntity entity);

    CityEntity toEntity(CityDTO dto);
}
