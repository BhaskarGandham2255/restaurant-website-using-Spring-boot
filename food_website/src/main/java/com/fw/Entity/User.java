package com.fw.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="USER_TAB")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL", nullable = false, unique = true)
	private String email;
	
	@Column(name="PASSWORD",nullable = false)
	private String password;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="USER_ROLE",
			joinColumns= @JoinColumn(name="USER_ID"),
			inverseJoinColumns = @JoinColumn(name="ROLE_ID")
			)
	private List<Role> roles;	
	
}
