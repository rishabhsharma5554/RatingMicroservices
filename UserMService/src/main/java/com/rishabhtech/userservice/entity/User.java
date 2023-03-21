package com.rishabhtech.userservice.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
	
	@Id
	private String userId;
	@Column(length = 25)
	private String name;
	private String email;
	private String about;
	
	@Transient
	private List<Rating> ratings;
}
