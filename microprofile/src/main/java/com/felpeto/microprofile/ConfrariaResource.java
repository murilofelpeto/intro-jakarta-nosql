package com.felpeto.microprofile;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.time.LocalDate;
import java.util.UUID;

@Path("confraria")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ConfrariaResource {

  @Inject
  private Confraria confraria;

  @POST
  public Developer add(NewDeveloper newDeveloper) {
    return confraria.save(
        new Developer(UUID.randomUUID().toString(),
            newDeveloper.name(), newDeveloper.birthday()));
  }

  public record NewDeveloper(String name, LocalDate birthday) {

  }
}
