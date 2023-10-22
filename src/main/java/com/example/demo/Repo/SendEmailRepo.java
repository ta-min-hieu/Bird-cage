package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entities.sales.Account;

@Repository
public interface SendEmailRepo extends JpaRepository<Account, Integer> {
	
	@Query(value = "select reset_password_token from sales.accounts where email = :email", nativeQuery = true)
	public String FindTokenByEmail(@Param("email") String email);
}
