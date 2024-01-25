package com.devsuperior.crudclientes.dto;

import com.devsuperior.crudclientes.models.Client;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;
    @NotEmpty(message = "Campo não pode ser vazio")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Data deve ser válida")
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO() {

    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    //Construtor para converter client para clientDTO
    public ClientDTO(Client model) {
        id = model.getId();
        name = model.getName();
        cpf = model.getCpf();
        income = model.getIncome();
        birthDate = model.getBirthDate();
        children = model.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}