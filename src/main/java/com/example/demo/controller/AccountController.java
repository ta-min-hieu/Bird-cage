package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.Entities.dbo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entities.sales.Account;
import com.example.demo.Service.AccountService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:3000")
public class AccountController {

	@Autowired
	private AccountService accservice;

	int count = 52;

	@GetMapping("/account")
	public Account getUserOrEmail(String username) {
		Account getUserorEmail = accservice.getUSernameorEmail(username);
		if (getUserorEmail != null) {
			return getUserorEmail;
		}
		return null;
	}

	@PostMapping("/getAccount")
	public Account getAccountByUsername(@RequestBody UserNameDTO DTO) {
		String username = DTO.getUsername();
		Account getAccount = accservice.getAccountByUserName(username);
		if (getAccount == null) {
			throw new RuntimeException("Not found");
		}
		return getAccount;

	}

	@PostMapping("/checklogin")
	public Account checkLogin(@RequestBody LoginDTO loginDTO) {
		String username = loginDTO.getUsername();
		String password = loginDTO.getPassword();
		Account checklogin = accservice.checkLogin(username, password);
		if (checklogin == null) {
			throw new RuntimeException("Not found account");
		}
		return checklogin;
	}

	@PostMapping("/register")
	public Account register(@RequestBody registerDTO registerDTO) {
		Account account = accservice.register(registerDTO);
		if (account == null) {
			throw new RuntimeException("Error at Account Controller");
		}
		return account;
	}

	@PostMapping("/registerVerify")
	public void registerVerify(@RequestBody TokenDTO DTO) {
		accservice.setEnable(DTO.getToken());
	}

	@GetMapping(value = {"/account-info"})
	public ResponseEntity<?> accountInfo(@RequestParam(name = "input", required = false) String input) {
		Account account = accservice.getAccountInfor(input);
		PageDto response = PageDto.builder()
				.code(200)
				.message("success")
				.object(account)
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
