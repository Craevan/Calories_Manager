package com.crevan.manager;

import org.springframework.lang.NonNull;
import org.springframework.test.context.support.DefaultActiveProfilesResolver;

import java.util.Arrays;


public class ActiveDbProfileResolver extends DefaultActiveProfilesResolver {

    @Override
    public @NonNull
    String[] resolve(final @NonNull Class<?> testClass) {
        String[] activeProfiles = super.resolve(testClass);
        String[] activeProfilesWithDb = Arrays.copyOf(activeProfiles, activeProfiles.length + 1);
        activeProfilesWithDb[activeProfiles.length] = Profiles.getActiveDbProfile();
        return activeProfilesWithDb;
    }
}
