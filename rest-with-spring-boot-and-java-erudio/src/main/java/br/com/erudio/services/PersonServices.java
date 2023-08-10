package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRespository;

@Service
public class PersonServices {

  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  private PersonRespository repository;

  public Person findById(Long id) {
    logger.info("Finding one person!");
    return repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundException("No records found for this ID!")
    );
  }

  public List<Person> findAll() {
    logger.info("Listing all people!");
    return repository.findAll();
  }

  public Person create(Person person) {
    logger.info("Creating a person!");
    return repository.save(person);
  }

  public Person update(Person person) {
    logger.info("Updating a person!");
    Person entity = repository.findById(person.getId())
      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return repository.save(person);
  }

  public void delete(Long id) {
    logger.info("Deleting a person!");
    Person entity = repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    repository.delete(entity);
  }
}
