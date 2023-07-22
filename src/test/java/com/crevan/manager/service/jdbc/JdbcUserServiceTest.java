package com.crevan.manager.service.jdbc;

import com.crevan.manager.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.crevan.manager.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
