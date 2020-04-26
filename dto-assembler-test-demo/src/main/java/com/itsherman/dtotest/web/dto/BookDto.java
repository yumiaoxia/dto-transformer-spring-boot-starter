package com.itsherman.dtotest.web.dto;

import com.itsherman.dtoassembler.annotations.DtoModel;
import com.itsherman.dtotest.domain.Book;

@DtoModel(from = Book.class)
public interface BookDto {

    String getBookName();
}
