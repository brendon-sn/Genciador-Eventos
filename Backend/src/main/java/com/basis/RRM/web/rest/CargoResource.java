package com.basis.RRM.web.rest;


import com.basis.RRM.service.CargoService;
import com.basis.RRM.service.dto.SelectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cargo")
public class CargoResource {
    private final CargoService cargoService;
    @GetMapping
    public ResponseEntity<List<SelectDTO>> mostrarCargos(){
        return ResponseEntity.ok(cargoService.mostarCargos());
    }
}
