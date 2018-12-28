package cs631.venmo.service;

import cs631.venmo.pojo.Families;

public interface FamiliesService extends BaseService<Families>{

	public Families loadFamilyById(Integer id);
}
