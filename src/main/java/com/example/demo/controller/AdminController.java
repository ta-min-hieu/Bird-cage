package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.registerDTO;
import com.example.demo.Entities.sales.Account;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {

	@Autowired
	private AdminService adminservie;
	@Autowired
	private AccountService accservice;

	@GetMapping("/customer")
	public ResponseEntity<List<Account>> getAllCustomer() {
		List<Account> accCustomer = adminservie.getAccountbyRole("customer");
		return ResponseEntity.ok(accCustomer);
	}

	@GetMapping("/staff")
	public ResponseEntity<List<Account>> getAllStaff() {
		List<Account> accStaff = adminservie.getAccountbyRole("staff");
		return ResponseEntity.ok(accStaff);
	}

	@GetMapping("/manager")
	public ResponseEntity<List<Account>> getAllManager() {
		List<Account> accManager = adminservie.getAccountbyRole("manager");
		return ResponseEntity.ok(accManager);
	}

	@PostMapping("/register")
	public Account register(@RequestBody registerDTO registerDTO) {
		Account account = adminservie.registerbyAdmin(registerDTO);
		if (account == null) {
			return null;
		}
		return account;
	}

	@GetMapping("search")
	public List<Account> search(@RequestParam String search) {
		List<Account> search1 = adminservie.search(search);
		return search1;
	}

	@PutMapping("/{id}")
	public Account updateAccount(@PathVariable String id, @RequestBody registerDTO DTO) {
		return accservice.updateAccount(id, DTO);
	}

	@DeleteMapping("/{id}")
	public void deleted(@PathVariable int id) {
		accservice.deleted(id);
	}
}
