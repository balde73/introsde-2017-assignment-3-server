package introsde.assignment3.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import introsde.assignment3.model.Activity;


/**
 * The persistent class for the "MeasureDefinition" database table.
 * 
 */
@Entity
@Table(name="\"ActivityType\"")
@NamedQuery(name="ActivityType.findByName", query="SELECT at FROM ActivityType at WHERE at.activityTypeName = :activityTypeName")
@XmlRootElement(name="activity_type")
public class ActivityType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"activityTypeName\"")
	private String activityTypeName;

	@OneToMany(mappedBy="type")
	private List<Activity> activities;

	public ActivityType() {
	}
	
	public ActivityType(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}

	@XmlValue
	public String getActivityTypeName() {
		return activityTypeName;
	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}

	@XmlTransient
	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}
