package introsde.assignment3.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import introsde.assignment3.model.Activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Person" database table.
 * 
 */
@Entity
@Table(name="\"Person\"")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@XmlRootElement(name="person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="\"idPerson\"")
	private Long id;

	@Column(name="\"lastname\"")
	private String lastname;

	@Column(name="\"firstname\"")
	private String firstname;

	@Column(name="\"username\"")
	private String username;
	
	@Column(name="\"birthdate\"")
	private String birthdate;
	
	@Column(name="\"email\"")
	private String email;

	// mappedBy must be equal to the name of the attribute in Activity that maps this relation
	@OneToMany(mappedBy="person", fetch=FetchType.EAGER)
	private List<Activity> activityPreferenceProfile;
	
	public Person() {
	}
	
	public Person(Person p) {
		this.setBirthdate(p.getBirthdate());
		this.setFirstname(p.getFirstname());
		this.setLastname(p.getLastname());
		this.setEmail(p.getEmail());
		this.setUsername(p.getUsername());
		this.setActivityPreferenceProfile(p.getActivityPreferenceProfile());
	}
	
	public Person(Long id, String lastname, String firstname, String username, String birthdate, String email) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.username = username;
		this.birthdate = birthdate;
		this.email = email;
	}

	@XmlSchemaType(name = "date")
	public Date getBirthdate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		
		if(this.birthdate==null)
			return null;
		try {
			startDate = df.parse(this.birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}

	public void setBirthdate(Date birthdate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(birthdate!=null)
			this.birthdate = df.format(birthdate);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		if(email!=null)
			this.email = email;
	}

	@XmlAttribute(name="id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long idPerson) {
		this.id = idPerson;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		if(lastname!=null)
			this.lastname = lastname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String name) {
		if(name!=null)
			this.firstname = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		if(username!=null)
			this.username = username;
	}

	// the XmlElementWrapper defines the name of node in which the list of activities elements
	// will be inserted
	@XmlElementWrapper(name="activities")
	@XmlElement(name="activity")
	public List<Activity> getActivityPreferenceProfile() {
	    return this.activityPreferenceProfile;
	}

	public void setActivityPreferenceProfile(List<Activity> param) {
		if(param!=null)
			this.activityPreferenceProfile = param;
	}
	
	public void addActivity(Activity activity) {
		if(this.activityPreferenceProfile == null) {
			this.activityPreferenceProfile = new ArrayList<Activity>();
		}
		this.activityPreferenceProfile.add(activity);
	}
	
}
