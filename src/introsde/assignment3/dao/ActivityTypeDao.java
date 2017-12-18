package introsde.assignment3.dao;

import introsde.assignment3.model.ActivityType;

public interface ActivityTypeDao extends Dao<ActivityType, String>{
	public ActivityType findByName(String activityTypeName);
}
