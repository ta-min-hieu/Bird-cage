package com.example.demo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.sales.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

	@Query(value = "select * from sales.accounts acc where acc.username = :username enable = 'true' or acc.email = :username and enable = 'true' ", nativeQuery = true)
	public Account getUsernameorEmail(@Param("username") String username);

	@Query(value = "select * from sales.accounts where username = :username and enable = 'true' and password = :password or email = :username and password = :password and enable = 'true'", nativeQuery = true)
	public Account checkLogin(@Param("username") String username, @Param("password") String password);

	@Query(value = "select * from sales.accounts where username = :username or email like :email", nativeQuery = true)
	public Account getUsernameOrEmail(@Param("username") String username, @Param("email") String email);

	@Query(value = "select MAX(account_id) FROM sales.accounts", nativeQuery = true)
	public int getAccountID();

	@Query(value = "select * from sales.accounts where account_id = :accountid", nativeQuery = true)
	public Account getAccountByID(@Param("accountid") String id);

	@Query(value = "select * from sales.accounts where account_id = :accountid", nativeQuery = true)
	public Account getAccountByID(@Param("accountid") int id);

	@Query(value = "select * from sales.accounts where reset_password_token = :token", nativeQuery = true)
	public Optional<Account> getAccountByToken(@Param("token") String token);

	@Query(value = "select * from sales.accounts where username = :username", nativeQuery = true)
	public Account getAccountByUserName(@Param("username") String username);

	@Query(value = "select * from sales.accounts where username = :input or account_id = :input", nativeQuery = true)
	public Account getAccountInfor(@Param("input") String input);
}
