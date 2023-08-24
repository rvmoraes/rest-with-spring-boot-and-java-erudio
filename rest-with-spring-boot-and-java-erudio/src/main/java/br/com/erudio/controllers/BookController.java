package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookServices;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/book/v1")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BookController {
  
  @Autowired
  private BookServices service;

  @GetMapping(value = "/{id}",
    produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
  @Operation(
    summary = "Finds a book", description = "Finds a book by id", tags = {"People"},
    responses = {
      @ApiResponse(description = "Success", responseCode = "200",
        content = @Content(schema = @Schema(implementation = BookVO.class))
      ),
      @ApiResponse(description = "No Content", responseCode = "204"),
      @ApiResponse(description = "Bad Request", responseCode = "400"),
      @ApiResponse(description = "Unauthorized", responseCode = "401"),
      @ApiResponse(description = "Not Found", responseCode = "404"),
      @ApiResponse(description = "Internal Error", responseCode = "500"),
    }
  )
    public BookVO findById(@PathVariable(value = "id") Long id) {
    return service.findById(id);
  }

  @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
  @Operation(
    summary = "Finds all books", description = "Finds all books recorded",
    responses = {
      @ApiResponse(description = "Success", responseCode = "200",
        content = {
          @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
          )
        }
      ),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
  )
  public List<BookVO> findAll() {
    return service.findAll();
  }

  @PostMapping(
    consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
    produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }
  )
  @Operation(
    summary = "Adds a book!", description = "Adds a book by passing in a JSON, XML or YAML",
    responses = {
      @ApiResponse(description = "Added", responseCode = "200",
        content = @Content(schema = @Schema(implementation = BookVO.class))
      ),
      @ApiResponse(description = "Bad Request", responseCode = "400"),
      @ApiResponse(description = "Unauthorized", responseCode = "401"),
      @ApiResponse(description = "Internal Error", responseCode = "500")
    }
  )
  public BookVO create(@RequestBody BookVO bookVO) {
    return service.create(bookVO);
  }

  @PutMapping(
    consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
    produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }
  )
  @Operation(
    summary = "Updates a book!", description = "Updates a book by passing a JSON, XML or YAML",
    responses = {
      @ApiResponse(description = "Updated", responseCode = "200",
        content = @Content(schema = @Schema(implementation = BookVO.class))
      ),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
  )
  public BookVO update(@RequestBody BookVO bookVO) {
    return service.update(bookVO);
  }

  @DeleteMapping(value = "/{id}")
  @Operation(
    summary = "Deletes a book!", description = "Deletes a book passing id in url path",
    responses = {
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    }
  )
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
