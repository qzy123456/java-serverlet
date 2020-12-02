package service.impl;

import entity.BookCase;
import repository.BookCaseRepository;
import repository.impl.BookCaseRepositoryImpl;
import service.BookCaseService;
import java.util.List;

public class BookCaseServiceImpl implements BookCaseService {
    private BookCaseRepository bookCaseRepository = new BookCaseRepositoryImpl();

    @Override
    public List<BookCase> findAll() {
        return bookCaseRepository.findAll();
    }
}
