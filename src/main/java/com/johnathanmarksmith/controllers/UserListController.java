package com.johnathanmarksmith.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.johnathanmarksmith.domain.User;

@Controller
public class UserListController {
	private List<User> userList = new ArrayList<User>(); 
	
	@RequestMapping(value="/AddUser.html",method=RequestMethod.GET)
	public String showForm(){
		return "AddUser";
	}
	
	@RequestMapping(value="/AddUser.html",method=RequestMethod.POST)
	public @ResponseBody String addUser(@ModelAttribute(value="user") User user, BindingResult result ){
		String returnText;
		if(!result.hasErrors()){
			userList.add(user);
			returnText = "User has been added to the list. Total number of users are " + userList.size();
		}else{
			returnText = "Sorry, an error has occur. User has not been added to list.";
		}
		return returnText;
	}

	@RequestMapping(value="/ShowUsers.html")
	public String showUsers(ModelMap model){
		model.addAttribute("Users", userList);
		return "ShowUsers";
	}
}
