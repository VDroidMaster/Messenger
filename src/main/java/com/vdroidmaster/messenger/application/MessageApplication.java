package com.vdroidmaster.messenger.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.vdroidmaster.messenger.resource.MessageResource;
import com.vdroidmaster.messenger.resource.ProfileResource;

@ApplicationPath("restapi")
public class MessageApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		
	    Set<Class<?>> resources = new HashSet<>();
	    
	    resources.add(MessageResource.class);
	    resources.add(ProfileResource.class);
	    
	    return resources;
	}
}
