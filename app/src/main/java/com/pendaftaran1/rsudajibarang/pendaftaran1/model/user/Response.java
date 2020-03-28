package com.pendaftaran1.rsudajibarang.pendaftaran1.model.user;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("token")
	private String token;

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"token = '" + token + '\'' + 
			"}";
		}
}