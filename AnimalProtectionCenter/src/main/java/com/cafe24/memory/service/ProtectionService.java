package com.cafe24.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.memory.domain.AnimalProtect;
import com.cafe24.memory.domain.ProtectionSpace;
import com.cafe24.memory.mapper.ProtectionMapper;

@Service
@Transactional
public class ProtectionService {

	@Autowired
	ProtectionMapper protectionMapper;
	
	public int insertAniamlProtection(AnimalProtect animalProtect) {
		int result = 0;
		result += protectionMapper.insertAniamlProtection(animalProtect);
		result += protectionMapper.updateAnimalProtectionIn(animalProtect);
		return result;
	}
	
	public ProtectionSpace modifyProtectionSpaceBySpace(String space) {
		ProtectionSpace ps = selectProtectionSpaceByCode(space);
		AnimalProtect ap = selectAnimalProtect(ps.getAnimalInsertCode());
		ps.setAnimalProtect(ap);
		
		return ps;
	}
	
	public int updateAnimalProtectionIn(ProtectionSpace proSpace) {
		List<ProtectionSpace> ps = protectionMapper.selectProtectionSpaceByAniInCode(proSpace.getAnimalProtect().getAnimalInsertCode());
		System.out.println(ps + " <-- ProtectionService.java");
		if(ps != null) {
			for(int i = 0; i < ps.size(); i++) {
				protectionMapper.updateAnimalProtectionExit(ps.get(i).getProtectSpaceCode());				
			}
		}
		return protectionMapper.updateAnimalProtectionIn(proSpace.getAnimalProtect());
	}
	
	public ProtectionSpace selectProtectionSpaceByCode(String proSpaceCode) {
		return protectionMapper.selectProtectionSpaceByCode(proSpaceCode);
	}
	
	public List<ProtectionSpace> selectProtectionSpace() {
		return protectionMapper.selectProtectionSpace();
	}
	
	public AnimalProtect selectAnimalProtect(String animalNum) {
		return protectionMapper.selectAnimalProtect(animalNum);
	}
	
}
