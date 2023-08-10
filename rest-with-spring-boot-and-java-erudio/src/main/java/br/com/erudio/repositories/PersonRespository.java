package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Person;

@Repository
public interface PersonRespository extends JpaRepository<Person, Long> {
  // Repositório JPA para a tabela person
}