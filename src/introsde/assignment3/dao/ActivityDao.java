package introsde.assignment3.dao;

import java.util.List;

import introsde.assignment3.model.Activity;
import introsde.assignment3.model.ActivityType;
import introsde.assignment3.model.Person;

public interface ActivityDao extends Dao<Activity, Long>{
	public List<Activity> getAllActivityByPersonAndType(Person p, ActivityType a);
	public Activity merge(Activity a);
}
