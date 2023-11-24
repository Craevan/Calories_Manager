package com.crevan.manager.service.jpa;

import com.crevan.manager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.crevan.manager.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaUserServiceTest extends AbstractUserServiceTest {
}
