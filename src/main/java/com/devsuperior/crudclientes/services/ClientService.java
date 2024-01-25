package com.devsuperior.crudclientes.services;

import com.devsuperior.crudclientes.dto.ClientDTO;
import com.devsuperior.crudclientes.models.Client;
import com.devsuperior.crudclientes.repositories.ClientRepository;
import com.devsuperior.crudclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clientList = repository.findAll(pageable);
        return clientList.map(ClientDTO::new); //Page já possui stream embutido
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return modelMapper.map(client, ClientDTO.class); //Conversão de client para clientDTO

    }

    @Transactional(readOnly = false)
    public ClientDTO createClient(ClientDTO dto){
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
        repository.save(client);

        return new ClientDTO(client);
    }

    @Transactional(readOnly = false)
    public ClientDTO updateClient(Long id, ClientDTO dto){

        try {
            Client client = repository.getReferenceById(id);
            client.setName(dto.getName());
            client.setCpf(dto.getCpf());
            client.setIncome(dto.getIncome());
            client.setBirthDate(dto.getBirthDate());
            client.setChildren(dto.getChildren());
            repository.save(client);

            return new ClientDTO(client);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

    }

    @Transactional(readOnly = false)
    public void deleteClient(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Cliente não encontrado");
        } else {
            repository.deleteById(id);
        }

    }

}