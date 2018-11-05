package org.sample.service;

import org.sample.dto.OnesProfileResult;
import org.sample.entity.Profile;

public interface ProfileService {

    Profile getProfileByUsername(String username);

    OnesProfileResult register(String username, String password, String nickname);
}
