package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRespository;

@Service
public class PersonServices {

  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  private PersonRespository repository;

  @Autowired
  private PersonMapper personMapper;

  public PersonVO findById(Long id) {
    logger.info("Finding one person!");
    Person person = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundException("No records found for this ID!")
    );
    PersonVO personVO = DozerMapper.parseObject(person, PersonVO.class);
    personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    return personVO;
  }

  public List<PersonVO> findAll() {
    logger.info("Listing all people!");
    List<PersonVO> people = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    people.stream().forEach(
      p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
    );
    return people;
  }

  public PersonVO create(PersonVO personVO) {
    logger.info("Creating a person!");
    Person person = DozerMapper.parseObject(personVO, Person.class);

    PersonVO vo = DozerMapper.parseObject(repository.save(person), PersonVO.class);
    vo.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
    return vo;
  }

  public PersonVOV2 createV2(PersonVOV2 personVO) {
    logger.info("Creating a person!");
    Person person = personMapper.convertVoToEntity(personVO);
    return personMapper.convertEntityToVo(repository.save(person));
  }

  public PersonVO update(PersonVO personVO) {
    logger.info("Updating a person!");
    Person person = repository.findById(personVO.getKey())
      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    person.setFirstName(personVO.getFirstName());
    person.setLastName(personVO.getLastName());
    person.setAddress(personVO.getAddress());
    person.setGender(personVO.getGender());

    PersonVO vo = DozerMapper.parseObject(repository.save(person), PersonVO.class);
    vo.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
    return vo;
  }

  public void delete(Long id) {
    logger.info("Deleting a person!");
    Person entity = repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    repository.delete(entity);
  }
}
