package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.azure.functions.annotation.*;

import lombok.Data;

import com.microsoft.azure.functions.*;

public class Function {

    @FunctionName("helloserverless")
    public String hello(@HttpTrigger(name = "hellorest", methods = { HttpMethod.GET }, route = "hello") String x) {

        return "Hello REST World!";
    }

    @FunctionName("createfuncionario")
    public Funcionario create(@HttpTrigger(name = "createfuncionariorest", methods = {
            HttpMethod.POST }, route = "funcionario") Funcionario funcionario) {

        funcionario.setId(UUID.randomUUID());

        return funcionario;
    }

    @FunctionName("updatefuncionario")
    public Funcionario update(@HttpTrigger(name = "updatefuncionariorest", methods = {
            HttpMethod.PUT }, route = "funcionario") Funcionario funcionario) {

        funcionario.setId(UUID.randomUUID());
        funcionario.setNome(funcionario.getNome() + " - updated!");

        return funcionario;
    }

    @FunctionName("readfuncionario")
    public List<Funcionario> read(@HttpTrigger(name = "readfuncionariorest", methods = {
            HttpMethod.GET }, route = "funcionario") Funcionario funcionario) {

        return Stream.of(new Funcionario("Maria", 30, 3000.0)).collect(Collectors.toList(new Funcionario()));
    }

    @FunctionName("deletefuncionario")
    public String delete(@HttpTrigger(name = "deletefuncionario", methods = {
        HttpMethod.DELETE }, route = "funcionario/{id}") int id) {

        return "deleted";
    }
}

@Data
class Funcionario {
    private UUID id;
    private String nome;
    private int idade;
    private double salario;

    Funcionario() {
    }

    Funcionario(String nome, int idade, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }
}