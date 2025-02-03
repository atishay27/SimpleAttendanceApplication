package dev.atishay.projects.attendance;

import dev.atishay.projects.attendance.dao.UserDAO;
import dev.atishay.projects.attendance.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import io.dropwizard.jdbi3.JdbiFactory;



public class AttendanceApplication extends Application<AttendanceConfiguration> {
    public static void main(String[] args) throws Exception {
        new AttendanceApplication().run(args);
    }

    @Override
    public void run(AttendanceConfiguration configuration, Environment environment) {
        // Add resources, health checks, etc., here
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

        final UserDAO userDAO = new UserDAO(jdbi);


        environment.jersey().register(new UserResource(userDAO));
        System.out.println("Dropwizard application has started!");
    }
}