package repository.impl;

import entity.Book;
import entity.BookCase;
import repository.BookRepository;
import utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll(int index,int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id order by book.id desc limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<Book>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),new BookCase(resultSet.getInt(9),resultSet.getString(10))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from book,bookcase where book.bookcaseid = bookcase.id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public int del(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "delete from book where id = ?";
        PreparedStatement statement = null;
        int res = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            res =  statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
        return  res;
    }

    @Override
    public Book findBookById(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id and book.id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book list = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               list = new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),new BookCase(resultSet.getInt(9),resultSet.getString(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public void insert(String name, String author, String publish, int pages, int price, int bookcaseid, int abled) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into book (name,author,publish,pages,price,bookcaseid,abled) values (?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,author);
            statement.setString(3,publish);
            statement.setInt(4,pages);
            statement.setInt(5,price);
            statement.setInt(6,bookcaseid);
            statement.setInt(7,abled);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }

    @Override
    public void updateById(String name, String author, String publish, int pages, int price, int bookcaseid, int abled, int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update book set `name` =? ,`author` =? ,`publish` =? ,`pages` =? ,`price` =?,`bookcaseid`=?,`abled`=?  where id= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,author);
            statement.setString(3,publish);
            statement.setInt(4,pages);
            statement.setInt(5,price);
            statement.setInt(6,bookcaseid);
            statement.setInt(7,abled);
            statement.setInt(8,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }
}
