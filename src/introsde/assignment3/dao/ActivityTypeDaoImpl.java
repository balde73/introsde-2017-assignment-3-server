package introsde.assignment3.dao;

import introsde.assignment3.model.ActivityType;

public class ActivityTypeDaoImpl extends JpaDao<ActivityType, String> implements ActivityTypeDao{

	public ActivityTypeDaoImpl() {
		super(ActivityType.class);
	}
	
	public ActivityType findByName(String activityTypeName) {
		return this.findById(activityTypeName);
	}

}
