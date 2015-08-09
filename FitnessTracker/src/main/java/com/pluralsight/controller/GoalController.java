package com.pluralsight.controller;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pluralsight.model.Goal;

@Controller
@SessionAttributes("goal")
public class GoalController {

	@RequestMapping(value = "addGoal", method = RequestMethod.GET)
	public String addGoal(Model model) {
		Goal goal = new Goal();
		goal.setMinutes(10);
		model.addAttribute("goal", goal);
		
		return "addGoal";
	}
	
	
	//create table permissions (
	//		username varchar(50) not null,
	//	    target varchar(50) not null,
	//	    permission varchar(50) not null,
	//	    constraint fk_permissions_users foreign key(username) references users(username));
		    
	//	create unique index ix_perm_username on permissions (username, target, permission);
		    
	//	insert into permissions (username, target, permission) values ("admin", "com.pluralsight.model.Goal", "createGoal");
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")  //  to exclude the rest
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#goal, 'createGoal')")
	@RequestMapping(value = "addGoal", method = RequestMethod.POST)
	public String updateGoal(@Valid @ModelAttribute("goal") Goal goal, BindingResult result) {
		
		System.out.println("result has errors: " + result.hasErrors());
		
		System.out.println("Goal set: " + goal.getMinutes());
		
		if(result.hasErrors()) {
			return "addGoal";
		}
		
		return "redirect:index.jsp";
	}
	
}
