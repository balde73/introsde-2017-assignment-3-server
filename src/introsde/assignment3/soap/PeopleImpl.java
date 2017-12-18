package introsde.assignment3.soap;

import java.util.List;

import javax.jws.WebService;

import introsde.assignment3.dao.ActivityDao;
import introsde.assignment3.dao.ActivityDaoImpl;
import introsde.assignment3.dao.ActivityTypeDao;
import introsde.assignment3.dao.ActivityTypeDaoImpl;
import introsde.assignment3.dao.PersonDao;
import introsde.assignment3.dao.PersonDaoImpl;
import introsde.assignment3.model.Activity;
import introsde.assignment3.model.ActivityType;
import introsde.assignment3.model.Person;

@WebService(endpointInterface = "introsde.assignment3.soap.People")
public class PeopleImpl implements People{
	private PersonDao personDao;
	private ActivityDao activityDao;
	private ActivityTypeDao activityTypeDao;
	
	
	public PeopleImpl() {
		this.personDao = new PersonDaoImpl();
		this.activityDao = new ActivityDaoImpl();
		this.activityTypeDao = new ActivityTypeDaoImpl();
	}

	@Override
	public List<Person> readPersonList() {
		return this.personDao.findAll();
	}

	@Override
	public Person readPerson(Long id) {
		return this.personDao.findById(id);
	}

	@Override
	public Person updatePerson(Person toPerson) {
		return this.personDao.update(toPerson);
	}

	@Override
	public Person createPerson(Person p) {
		return this.personDao.add(p);
	}

	@Override
	public boolean deletePerson(Long id) {
		return this.personDao.remove(id);
	}

	@Override
	public List<Activity> readPersonPreferences(Long id, String activity_type) {
		Person p = this.personDao.findById(id);
		ActivityType a = this.activityTypeDao.findById(activity_type);
		return this.activityDao.getAllActivityByPersonAndType(p, a);
	}

	@Override
	public List<Activity> readPreferences() {
		return this.activityDao.findAll();
	}

	@Override
	public Activity readPersonPreference(Long id, Long activity_id) {
		Activity activity = this.activityDao.findById(activity_id);
		if(activity == null)
			return null;
		Person p = activity.getPerson();
		if(p == null)
			return null;
		return id.equals(p.getId()) ? activity : null;
	}

	@Override
	public Activity savePersonPreferences(Long id, Activity activity) {
		Person person = personDao.findById(id);
		ActivityType at = activity.getActivityType();
		ActivityType stored_at = activityTypeDao.findById(at.getActivityTypeName());
		if(stored_at == null)
			stored_at = this.activityTypeDao.add(at);
		activity.setActivityType(at);
		activity.setPerson(person);
		person.addActivity(activity);
		return this.activityDao.add(activity);
	}

	@Override
	public Activity updatePersonPreferences(Long id, Activity toActivity) {
		Person person = this.personDao.findById(id);
		if(person==null)
			return null;
		toActivity.setPerson(person);
		return this.activityDao.merge(toActivity);
	}

	@Override
	public Activity evaluatePersonPreferences(Long id, Activity activity, int value) {
		activity.setRate(value);
		return this.updatePersonPreferences(id, activity);
	}

	@Override
	public List<Activity> getBestPersonPreference(Long id) {
		Person p = personDao.findById(id);
		List<Activity> activities = p.getActivityPreferenceProfile();
		activities.sort((a, b) -> Integer.compare(b.getRate(),a.getRate()));
		return activities;
	}
	
}
