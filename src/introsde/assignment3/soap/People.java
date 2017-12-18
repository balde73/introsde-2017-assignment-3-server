package introsde.assignment3.soap;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import introsde.assignment3.model.Activity;
import introsde.assignment3.model.Person;

//Service Endpoint Interface
@WebService
@SOAPBinding(style =  Style.DOCUMENT, use=Use.LITERAL)
public interface People {
	
	@WebResult(name="person") 
	@WebMethod
	public List<Person> readPersonList();
	
	@WebMethod
	public Person readPerson(Long id);
	
	@WebMethod
	public Person updatePerson(Person p);
	
	@WebMethod
	public Person createPerson(Person p);

	@WebMethod
	public boolean deletePerson(Long id);

	@WebMethod
	public List<Activity> readPersonPreferences(Long id, String activity_type);

	@WebMethod
	public List<Activity> readPreferences();

	@WebMethod
	public Activity readPersonPreference(Long id, Long activity_id);
	
	@WebMethod
	public Activity savePersonPreferences(Long id, Activity activity);

	@WebMethod
	public Activity updatePersonPreferences(Long id, Activity activity);
	
	@WebMethod
	public Activity evaluatePersonPreferences(Long id, Activity activity, int value);
	
	@WebMethod
	public List<Activity> getBestPersonPreference(Long id);
	
	@WebMethod
	public void resetDB();
}
