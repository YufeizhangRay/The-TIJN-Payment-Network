package cs631.venmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs631.venmo.pojo.Families;

@Service("familiesService")
public class FamiliesServiceImpl extends BaseServiceImpl<Families> implements FamiliesService{

	@Autowired
	private FamiliesService familiesService;

	@Override
	public Families loadFamilyById(Integer id) {
		return familiesService.loadFamilyById(id);
	}
}
