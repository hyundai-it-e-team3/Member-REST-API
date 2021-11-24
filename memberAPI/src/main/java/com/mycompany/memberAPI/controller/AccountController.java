package com.mycompany.memberAPI.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.memberAPI.dto.Account;
import com.mycompany.memberAPI.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

	@Resource
	private AccountService accountService;
	
	@GetMapping
	public List<Account> getAccount(@RequestBody String memberId) {
		log.info("결제수단 조회");
		List<Account> account = accountService.getAccount(memberId);
		return account;
	}
	
	@PostMapping
	public void insertAccount(@RequestBody Account account) {
		log.info("결제수단 추가");
		accountService.insertAccount(account);
	}
	
	@DeleteMapping
	public void deleteAccount(@RequestBody String accountNo) {
		log.info("결제수단 삭제");
		accountService.deleteAccount(accountNo);
	}
	
}
