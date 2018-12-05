package org.sample.webapp.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.webapp.dao.ProfileDAO;
import org.sample.webapp.dao.impl.ProfileDAOImpl;
import org.sample.webapp.dto.OPResult;
import org.sample.webapp.dto.Validation;
import org.sample.webapp.entity.Profile;
import org.sample.webapp.enums.OPEnum;
import org.sample.webapp.dao.ConnectionProxy;
import org.sample.webapp.service.ProfileService;
import org.sample.webapp.util.FormatValidator;

public class ProfileServiceImpl implements ProfileService {

    public static final ProfileService INSTANCE = new ProfileServiceImpl();

    private ProfileServiceImpl() {}

    private static final Logger LOGGER = LogManager.getLogger();

    private final ProfileDAO profileDAO = ProfileDAOImpl.INSTANCE;

    @Override
    public OPResult register(java.lang.String username, java.lang.String password, java.lang.String nickname) {
        Validation validation = FormatValidator.validateRegistration(username, password, nickname);
        if (!validation.isSuccess()) {
            // 校验失败返回false以及失败的理由
            return new OPResult(false, validation.getError());
        }

        Profile profile = new Profile(username, password, nickname);
        try {
            LOGGER.info("register - saveProfile began: {}", profile);
            final long start = System.currentTimeMillis();
            int i = profileDAO.saveProfile(profile);
            final long end = System.currentTimeMillis();
            LOGGER.info("register - end of saveProfile: result={}, {}ms", i, end - start);
            if (i == 0) {
                ConnectionProxy.close();
                return new OPResult(false, OPEnum.PROFILE_EXISTED.getStateInfo());
            } else {
                profile = profileDAO.getByUsername(username);
                ConnectionProxy.close();
                return new OPResult(true, profile);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while registering", e);
            return new OPResult(false, OPEnum.INNER_ERROR.getStateInfo());
        }
    }
}
