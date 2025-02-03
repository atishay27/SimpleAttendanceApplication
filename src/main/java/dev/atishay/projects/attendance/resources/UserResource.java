package dev.atishay.projects.attendance.resources;

import dev.atishay.projects.attendance.models.User;
import dev.atishay.projects.attendance.dao.UserDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    public Response createUser(User user) {
        long userId = userDAO.createUser(user);
        user.setUserID(userId); // Lombok's @Setter is used here
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") long id) {
        Optional<User> user = userDAO.getUserById(id);
        return user.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") long id, User updatedUser) {
        boolean updated = userDAO.updateUser(id, updatedUser);
        if (updated) {
            return Response.ok().entity(updatedUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        boolean deleted = userDAO.deleteUser(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
