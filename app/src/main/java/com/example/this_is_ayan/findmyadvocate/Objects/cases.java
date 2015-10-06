package com.example.this_is_ayan.findmyadvocate.Objects;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("cases")
public class cases extends ParseObject{
	public cases(){
		
	}
	

	public void setUser(ParseUser currentUser) {
		put("user", currentUser);
	}
}
