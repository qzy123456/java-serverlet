package repository;

import entity.BookCase;

import java.util.List;

public interface BookCaseRepository {
    public List<BookCase> findAll();
}
