package com.devsuperior.crudclientes.controllers;

import com.devsuperior.crudclientes.dto.ClientDTO;
import com.devsuperior.crudclientes.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public Page<ClientDTO> findAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public ClientDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO dto){
        dto = service.createClient(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri()).body(dto); //Código 201 http
    }

    @PutMapping(value = "/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO dto){
        return service.updateClient(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        service.deleteClient(id);
        return ResponseEntity.noContent().build();

    }

}