package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;

@Service
public class BookServices {
  
  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  private BookRepository repository;

  public BookVO findById(Long id) {
    logger.info("Finding one book!");
    Book book = repository.findById(id).orElseThrow(
      () -> new ResourceNotFoundException("No records found for this id!")
    );
    BookVO vo = DozerMapper.parseObject(book, BookVO.class);
    vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
    return vo;
  }

  public List<BookVO> findAll() {
    logger.info("Listing all books!");
    List<BookVO> books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
    books.stream().forEach(
      b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel())
    );
    return books;
  }

  public BookVO create(BookVO bookVO) {
    logger.info("Creating a book!");
    Book book = DozerMapper.parseObject(bookVO, Book.class);
    bookVO = DozerMapper.parseObject(
      repository.save(book),
      BookVO.class
    );
    bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
    return bookVO;
  }

  public BookVO update(BookVO bookVO) {
    logger.info("Updating a book!");
    Book book = repository.findById(bookVO.getKey())
      .orElseThrow(() -> new ResourceNotFoundException("Book doesn't exist!"));

    book.setAuthor(bookVO.getAuthor());
    book.setLauchDate(bookVO.getLauchDate());
    book.setPrice(bookVO.getPrice());
    book.setTitle(bookVO.getTitle());
    repository.save(book);

    bookVO = DozerMapper.parseObject(book, BookVO.class);
    bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
    return bookVO;
  }

  public void delete(Long id) {
    logger.info("Deleting a book!");
    Book book = repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Book doesn't exist!"));
    repository.delete(book);
  }

}
