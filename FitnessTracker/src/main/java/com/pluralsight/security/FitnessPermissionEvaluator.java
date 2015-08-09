package com.pluralsight.security;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class FitnessPermissionEvaluator implements PermissionEvaluator {

	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		Object[] args = {
				((User) auth.getPrincipal()).getUsername()
				, targetDomainObject.getClass().getName()
				, permission.toString()
		};
		
		int count = template.queryForObject("select count(*) from permissions p "
				+ "where p.username = ? and p.target = ? and p.permission = ?", args, Integer.class);
		
		if(count == 1){
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
		return false;
	}

}
