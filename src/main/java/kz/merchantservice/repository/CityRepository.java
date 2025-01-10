package kz.merchantservice.repository;

import kz.merchantservice.model.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, UUID> {

    boolean existsByNameIgnoreCase(String name);
}
