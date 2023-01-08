package com.niit.demo1.repository;

import com.niit.demo1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,String> {
    public List<User>findByfirstName(String firstname);

}
