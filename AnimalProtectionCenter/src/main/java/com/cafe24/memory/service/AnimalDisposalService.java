package com.cafe24.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.memory.domain.AnimalDisposal;
import com.cafe24.memory.mapper.AnimalDisposalMapper;


@Service
@Transactional
public class AnimalDisposalService {

	@Autowired private AnimalDisposalMapper animalDisposalMapper;
	
	public AnimalDisposal selectResultTest(String send_code){
		return animalDisposalMapper.selectResultTest(send_code);
	}
	
	public List<AnimalDisposal> selectResultTest(){
		return animalDisposalMapper.selectResultTest();
	}
}