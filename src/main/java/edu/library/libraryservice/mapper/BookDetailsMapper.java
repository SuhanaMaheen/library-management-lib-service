package edu.library.libraryservice.mapper;

import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.model.BookDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDetailsMapper {


    public BookDetails toEntity(BookDetailsDto dto) {
        if (dto == null) {
            return null;
        }
        BookDetails entity = new BookDetails();
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        return entity;
    }
    public List<BookDetailsDto> toDtoList(List<BookDetails> entity) {
        if (entity == null) {
            return null;
        }
        List<BookDetailsDto> dtoList = new ArrayList<BookDetailsDto>();
        for(BookDetails book : entity) {
            BookDetailsDto dto = new BookDetailsDto();
            dto.setAuthor(book.getAuthor());
            dto.setTitle(book.getTitle());
            dto.setCategory(book.getCategory());
            dto.setDescription(book.getDescription());
            dtoList.add(dto);
        }
        return dtoList;
    }


}
