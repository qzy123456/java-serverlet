package repository;

import entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index,int limit);
    public int count();
    public int del(int id);
    public Book findBookById(int id);
    public void insert(String name, String author, String publish, int pages, int price, int bookcaseid, int abled);
    public void updateById(String name, String author, String publish, int pages, int price, int bookcaseid, int abled,int id);
}
