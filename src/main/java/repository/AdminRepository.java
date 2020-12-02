package repository;

import entity.Admin;

public interface AdminRepository {
    public Admin login(String username,String password);
}
