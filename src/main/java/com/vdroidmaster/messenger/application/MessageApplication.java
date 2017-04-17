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
		
	    Set<Class<?>> classes = new HashSet<>();
	    
	    classes.add(MessageResource.class);
	    classes.add(ProfileResource.class);
	    
	    return classes;
	}
}
