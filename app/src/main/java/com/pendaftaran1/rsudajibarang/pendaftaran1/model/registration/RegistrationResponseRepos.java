package com.pendaftaran1.rsudajibarang.pendaftaran1.model.registration;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponseRepos{

	@SerializedName("metaData")
	private MetaData metaData;

	@SerializedName("response")
	private Object response;

	public void setMetaData(MetaData metaData){
		this.metaData = metaData;
	}

	public MetaData getMetaData(){
		return metaData;
	}

	public void setResponse(Object response){
		this.response = response;
	}

	public Object getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"RegistrationResponseRepos{" + 
			"metaData = '" + metaData + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}