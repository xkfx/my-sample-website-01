package org.sample.webapp.service;

import org.sample.webapp.dto.ServiceResult;

public interface ProfileService {

    ServiceResult register(String username, String password, String nickname);
}
