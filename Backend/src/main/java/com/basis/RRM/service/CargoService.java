package com.basis.RRM.service;


import com.basis.RRM.repository.CargoRepository;
import com.basis.RRM.service.dto.SelectDTO;
import com.basis.RRM.service.mapper.CargoSelectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CargoService {

private final CargoRepository cargoRepository;
private final CargoSelectMapper cargoSelectMapper;


public List<SelectDTO> mostarCargos(){

    return cargoSelectMapper.toDto(cargoRepository.findAll());
}
}
