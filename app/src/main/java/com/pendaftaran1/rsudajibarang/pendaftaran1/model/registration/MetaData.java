package com.pendaftaran1.rsudajibarang.pendaftaran1.model.registration;

import com.google.gson.annotations.SerializedName;

public class MetaData{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"MetaData{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}