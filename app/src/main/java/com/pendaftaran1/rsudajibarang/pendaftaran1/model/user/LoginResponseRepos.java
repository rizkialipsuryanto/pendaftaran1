package com.pendaftaran1.rsudajibarang.pendaftaran1.model.user;

import com.google.gson.annotations.SerializedName;

public class LoginResponseRepos{

	@SerializedName("metaData")
	private MetaData metaData;

	@SerializedName("response")
	private Response response;

	public void setMetaData(MetaData metaData){
		this.metaData = metaData;
	}

	public MetaData getMetaData(){
		return metaData;
	}

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponseRepos{" + 
			"metaData = '" + metaData + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}