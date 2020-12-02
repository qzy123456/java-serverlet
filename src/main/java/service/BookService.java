package service;

import entity.Book;
import entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
    public int getBorrowPages(Integer readerid);
    public void addBorrow(Integer bookid,Integer readerid);
    public List<Borrow> findAllBorrowByReaderId(Integer id,Integer page);
    public List<Borrow> findAllBorrowByState(Integer state,Integer page);
    public int getBorrowPagesByState(Integer state);
    public void handleBorrow(Integer borrowId,Integer state,Integer adminId);
    public int isApply(Integer bookId, Integer readerId);
    public int del(int id);
    public Book findBookById(int id);
    public void   insert(String name,String author,String publish, int pages, int price, int bookcaseid,int abled);
    public void updateById(String name, String author, String publish, int pages, int price, int bookcaseid, int abled,int id);
}
