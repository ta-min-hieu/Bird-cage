package com.example.demo.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.sales.Account;

@Repository
public interface AdminRepo extends JpaRepository<Account, Integer> {

	@Query(value = "select * from sales.accounts where role = :role and enable = 'true'", nativeQuery = true)
	public List<Account> findByRole(String role);

	@Query(value = "select * from sales.accounts where first_name like %:search% or last_name like %:search% or phone like %:search%", nativeQuery = true)
	public List<Account> Search(@Param("search") String search);

}
