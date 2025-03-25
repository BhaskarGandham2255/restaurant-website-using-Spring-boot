package com.fw.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fw.Entity.User;

public class CustmerUserDetails implements UserDetails
{
	
	private User user;
	
	public CustmerUserDetails(User user)
	{
		this.user=user;
//		this.user.setId(user.getId());
//		this.user.setFirstName(user.getFirstName());
//		this.user.setLastName(user.getLastName());
//		this.user.setEmail(user.getEmail());
//		this.user.setPassword(user.getPassword());
//		this.user.setRoles(user.getRoles());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		user.getRoles().forEach(role->
		{
			list.add(new SimpleGrantedAuthority(role.getName()));
		});
		return list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}


}
