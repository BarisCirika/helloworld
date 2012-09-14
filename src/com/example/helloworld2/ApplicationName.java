package com.example.helloworld2;

import java.io.Serializable;



public class ApplicationName implements Serializable{

	private String name;
	public ApplicationName() {
		// TODO Auto-generated constructor stub
		this.name = "Hello World 2.3.3";
	}
	public ApplicationName(String name){
		this.name = name;
	}
	public String getApplicationName(){
		return name;
	}
}
