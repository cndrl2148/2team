package com.cafe24.memory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.memory.domain.Deposit;

@Mapper
public interface DepositMapper {
	
	public List<Deposit> selectDeposit();
	
}
