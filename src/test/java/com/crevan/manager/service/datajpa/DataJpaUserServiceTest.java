package com.crevan.manager.service.datajpa;

import com.crevan.manager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.crevan.manager.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
