package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erudio.model.Person;

public interface PersonRespository extends JpaRepository<Person, Long> {
  // Repositório JPA para a tabela person
}