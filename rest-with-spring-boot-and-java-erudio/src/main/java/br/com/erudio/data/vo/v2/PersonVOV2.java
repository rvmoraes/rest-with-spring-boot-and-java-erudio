package br.com.erudio.data.vo.v2;

import java.io.Serializable;
import java.util.Date;

public class PersonVOV2 implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;
  private Date birthday;

  public PersonVOV2() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return this.birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
 
}
