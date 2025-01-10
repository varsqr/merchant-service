package kz.merchantservice.controller;

import jakarta.validation.Valid;
import kz.merchantservice.exception.CustomException;
import kz.merchantservice.model.dto.CityDTO;
import kz.merchantservice.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("cities/v1")
@RequiredArgsConstructor
public class CityController {

    private final CityService service;

    @PostMapping
    public CityDTO saveOne(@RequestBody @Valid CityDTO dto) throws CustomException {
        return service.saveOne(dto);
    }

    @GetMapping("/{id}")
    public CityDTO getOne(@PathVariable UUID id) throws CustomException {
        return service.getOne(id);
    }

    @PutMapping("/{id}")
    public CityDTO updateOne(@PathVariable UUID id, @RequestBody @Valid CityDTO dto) throws CustomException {
        return service.updateOne(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) throws CustomException {
        service.deleteOne(id);
    }
}
