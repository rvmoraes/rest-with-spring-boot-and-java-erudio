package br.com.erudio.services.unittests.mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRespository;
import br.com.erudio.services.PersonServices;
import br.com.erudio.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {
  
  MockPerson input;

  @InjectMocks
  private PersonServices service;

  @Mock
  PersonRespository respository;

  @BeforeEach
  void setUpMocks() throws Exception {
    input = new MockPerson();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindById() {
    Person person = input.mockEntity();
    person.setId(1L);

    when(respository.findById(1L)).thenReturn(Optional.of(person));
    var result = service.findById(1L);
    assertNotNull(result);
    assertNotNull(result.getKey());
    System.out.println(result.getLinks());
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains(""));
  }

}
