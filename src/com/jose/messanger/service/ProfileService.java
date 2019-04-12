package com.jose.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jose.messanger.database.DatabaseClass;
import com.jose.messanger.model.ErrorMessage;
import com.jose.messanger.model.Message;
import com.jose.messanger.model.Profile;

public class ProfileService {
	
private Map<String, Profile>  profiles= DatabaseClass.getProfiles();
	
	public ProfileService() {
		super();
		
		profiles.put("jose", new Profile(1l, "joseph","wambugu","kibe"));
	}

	public List<Profile> getAllProfiles(){
		
		return new ArrayList<Profile>(profiles.values());
	}
		
	public Profile getProfile(String profileName) {
		
		ErrorMessage errorMessage = new ErrorMessage("The profile name you provided was not found", 404,"the documentatiuon is not available");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		Profile name = profiles.get(profileName);
		if(name == null) {
			throw new WebApplicationException(response);
		}
		return name;
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() +1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfilee(Profile profile) {
		if(profile.getFirstName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	
	}
}
