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
import com.mycompany.memberAPI.service.AccountService.InsertAccountResult;

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
	public String insertAccount(@RequestBody Account account) {
		log.info("결제수단 추가");
		
		//결제수단 추가 시 결제비밀번호가 존재하지 않을 경우 결제비밀번호 추가를 위한 처리
		InsertAccountResult iar = accountService.insertAccount(account);

		String result;
		if(iar == InsertAccountResult.SUCCESS) {
			result = "success";
		} else {
			result = "fail";
		}
		
		return result;
	}
	
	@DeleteMapping
	public void deleteAccount(@RequestBody String accountNo) {
		log.info("결제수단 삭제");
		accountService.deleteAccount(accountNo);
	}
	
}
