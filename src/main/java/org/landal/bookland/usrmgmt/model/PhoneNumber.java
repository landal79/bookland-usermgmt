package org.landal.bookland.usrmgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PHONES")
public class PhoneNumber extends AbstractEntity {
	
	
	@NotNull
	@Column(name = "phone_number")
	private String phoneNumber;



	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
