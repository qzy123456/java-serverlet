package repository.impl;

import entity.Book;
import entity.BookCase;
import repository.BookCaseRepository;
import repository.BookRepository;
import utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCaseRepositoryImpl implements BookCaseRepository {
    @Override
    public List<BookCase> findAll() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookcase";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BookCase> list = new ArrayList<BookCase>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new BookCase(resultSet.getInt("id"),resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }
}
