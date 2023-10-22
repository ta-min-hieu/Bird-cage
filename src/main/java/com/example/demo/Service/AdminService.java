package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.registerDTO;
import com.example.demo.Entities.sales.Account;
import com.example.demo.Repo.AccountRepo;
import com.example.demo.Repo.AdminRepo;

@Service
public class AdminService {
	@Autowired
	private AdminRepo adminrepo;
	@Autowired
	private AccountRepo accrepo;

	public List<Account> getAccountbyRole(String role) {
		return adminrepo.findByRole(role);
	}

	public Account registerbyAdmin(registerDTO registerDTO) {
		String username = registerDTO.getUsername();
		String email = registerDTO.getEmail();
		if (accrepo.getUsernameorEmail(username) != null) {
			return null;
		}
		if (accrepo.getUsernameorEmail(email) != null) {
			return null;
		}
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(registerDTO.getPassword());
		account.setEmail(email);
		account.setFirstName(registerDTO.getFirstName());
		account.setLastName(registerDTO.getLastName());
		account.setPhone(registerDTO.getPhone());
		account.setRole(registerDTO.getRole());
		return accrepo.save(account);
	}

	public List<Account> search(String search) {
		return adminrepo.Search(search);
	}
}
