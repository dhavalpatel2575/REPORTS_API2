package com.jeku.runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jeku.entity.EligibilityDetails;
import com.jeku.repo.EligibilityDetailsRepo;

@Component
public class AppRunner implements ApplicationRunner{

	@Autowired
	private EligibilityDetailsRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		EligibilityDetails entity1=new EligibilityDetails();
		entity1.setEligId(1);
		entity1.setName("John");
		entity1.setEmail("jeksjackass@gmail.com");
		entity1.setMobile(999866972l);
		entity1.setGender('M');
		entity1.setSsn(4992l);
		entity1.setPlanName("SNAP");
		entity1.setPlanStatus("Approved");
		repo.save(entity1);
		
		EligibilityDetails entity2=new EligibilityDetails();
		entity2.setEligId(2);
		entity2.setName("Jack");
		entity2.setEmail("jeksjackass@gmail.com");
		entity2.setMobile(999866972l);
		entity2.setGender('M');
		entity2.setSsn(4992l);
		entity2.setPlanName("SNAP");
		entity2.setPlanStatus("Approved");
		repo.save(entity2);
		
		EligibilityDetails entity3=new EligibilityDetails();
		entity3.setEligId(3);
		entity3.setName("Jojo");
		entity3.setEmail("jeksjackass@gmail.com");
		entity3.setMobile(999866972l);
		entity3.setGender('M');
		entity3.setSsn(4992l);
		entity3.setPlanName("SNAP");
		entity3.setPlanStatus("Approved");
		repo.save(entity3);	
	}
}