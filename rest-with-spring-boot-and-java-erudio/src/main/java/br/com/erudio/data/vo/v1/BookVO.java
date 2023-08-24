package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id", "author", "lauchDate", "price", "title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {
  
  @Mapping("id")
  @JsonProperty(value = "id")
  private Long key;

  @JsonProperty(value = "author")
  private String author;

  @JsonProperty(value = "lauchDate")
  private Date lauchDate;

  @JsonProperty(value = "price")
  private double price;

  @JsonProperty(value = "title")
  private String title;

  public BookVO() {
  }

  public Long getKey() {
    return this.key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getLauchDate() {
    return this.lauchDate;
  }

  public void setLauchDate(Date lauchDate) {
    this.lauchDate = lauchDate;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
