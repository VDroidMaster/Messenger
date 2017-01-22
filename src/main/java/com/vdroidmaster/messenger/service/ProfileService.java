package com.vdroidmaster.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.vdroidmaster.messenger.database.DatabaseClass;
import com.vdroidmaster.messenger.model.Profile;

public class ProfileService {

	private static ProfileService profileService = new ProfileService();
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	private ProfileService() {

		Profile p1 = new Profile("droidMaster1", "Droid1", "Master1");
		Profile p2 = new Profile("droidMaster2", "Droid2", "Master2");

		addProfile(p1);
		addProfile(p2);
	}

	public static ProfileService getInstance() {
		return profileService;
	}

	public List<Profile> getProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {

		if (StringUtils.isEmpty(profileName)) {
			return null;
		}
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {

		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {

		if (StringUtils.isEmpty(profile.getProfileName())) {
			return null;
		}

		if (profiles.replace(profile.getProfileName(), profile) != null) {
			return profile;
		} else {
			return null;
		}
	}

	public void removeProfile(String profileName) {

		if (StringUtils.isEmpty(profileName)) {
			return;
		}

		profiles.remove(profileName);
	}
}
