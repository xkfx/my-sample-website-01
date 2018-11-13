package org.sample.webapp.service;

import org.sample.webapp.dto.OPResult;
import org.sample.webapp.entity.Profile;

public interface ProfileService {

    Profile getByUsername(String username);

    OPResult register(String username, String password, String nickname);
}
