package com.carRepair.carRepair.Repositories;

import com.carRepair.carRepair.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    List<User> findAll();

    User findOne(Long id);




}