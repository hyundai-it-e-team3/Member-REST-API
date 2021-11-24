package com.mycompany.memberAPI.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.memberAPI.dto.Account;

@Mapper
public interface AccountDao {
	public List<Account> getAccount(String memberId);
	public void insertAccount(Account account);
	public void deleteAccount(String accountNo);
}
