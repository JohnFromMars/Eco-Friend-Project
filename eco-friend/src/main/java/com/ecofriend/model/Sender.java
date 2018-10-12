package com.ecofriend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "sender")
public class Sender {
	
	@Id
	@Column(name = "senderId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long senderId;

	@OneToOne(targetEntity = SiteUser.class)
	@JoinColumn(name = "user_id", nullable = false)
	private SiteUser user;

	@Column(name = "licensNumber")
	private String licensNumber;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "taxNumber")
	private String taxNumber;

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public SiteUser getSiteUser() {
		return user;
	}

	public void setSiteUser(SiteUser siteUser) {
		this.user = siteUser;
	}

	public String getLicensNumber() {
		return licensNumber;
	}

	public void setLicensNumber(String licensNumber) {
		this.licensNumber = licensNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	@Override
	public String toString() {
		return "Sender [senderId=" + senderId + ", user=" + user + ", licensNumber=" + licensNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", taxNumber=" + taxNumber + "]";
	}

}
