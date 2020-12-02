package repository;

import entity.Reader;

public interface ReaderRepository {
    public Reader login(String username,String password);
}
