package service;

import entity.Book;
import entity.BookCase;
import entity.Borrow;

import java.util.List;

public interface BookCaseService {
    public List<BookCase> findAll();
}
