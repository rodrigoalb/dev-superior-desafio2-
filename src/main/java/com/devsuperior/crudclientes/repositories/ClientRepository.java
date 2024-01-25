package com.devsuperior.crudclientes.repositories;

import com.devsuperior.crudclientes.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
