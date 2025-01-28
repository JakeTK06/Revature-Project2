package com.revature.planetarium.controller;

import java.util.List;
import java.util.Map;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.service.planet.PlanetService;

import io.javalin.http.Context;

public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    public void findAll(Context ctx) {
        List<Planet> planets = planetService.selectAllPlanets();
        ctx.json(planets);
        ctx.status(200);
    }

    public void findAllByOwner(Context ctx) {
        int ownerId = Integer.parseInt(ctx.pathParam("ownerId"));
        List<Planet> planets = planetService.selectByOwner(ownerId);
        ctx.json(planets);
        ctx.status(200);
    }

    public void findByIdentifier(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            Planet planet;
            if(identifier.matches("^[0-9]+$")) {
                planet = planetService.selectPlanet(Integer.parseInt(identifier));
            } else {
                planet = planetService.selectPlanet(identifier);
            }
            ctx.json(planet);
            ctx.status(200);
        } catch (PlanetFail e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    }

    public void createPlanet(Context ctx) {
        try {
            Planet planet = ctx.bodyAsClass(Planet.class);
            planetService.createPlanet(planet);
            ctx.status(201);            
        } catch (PlanetFail e) {
            ctx.json(Map.of("message", e.getMessage()));
            ctx.status(400);
        }

    }

    public void updatePlanet(Context ctx){

        String planetId= ctx.queryParam("planetId");
        if(planetId == null || planetId.isEmpty()) {
            ctx.status(400);  // Bad Request
            return;
        }
        try {
            Planet newPlanet = ctx.bodyAsClass(Planet.class);
            newPlanet.setPlanetId(Integer.parseInt(planetId));
            Planet updatedPlanet = planetService.updatePlanet(newPlanet);
            ctx.json(updatedPlanet);
            ctx.status(201);
        } catch (PlanetFail e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }

    }

    public void deletePlanet(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            String responseMessage;
            responseMessage = String.valueOf(planetService.deletePlanet(identifier));
            ctx.json(responseMessage);
            ctx.status(204);
        } catch (PlanetFail e) {
            ctx.json(Map.of("message", e.getMessage()));
            ctx.status(404);
        }
    }

    public void checkExistingPlanet(Context ctx) {
        String planetName = ctx.queryParam("planetName");

        // Check if the username is provided
        if (planetName == null || planetName.isEmpty()) {
            ctx.status(400);  // Bad Request
            ctx.json(Map.of("message", "Username is required"));
            return;
        }

        try {
            boolean foundUser = planetService.checkName(planetName);  // Check if the user exists
            ctx.status(200);  // OK status
            ctx.json(foundUser);  // Return the result (true or false)
        } catch (UserFail e) {
            ctx.status(401);  // Unauthorized if user not found
            ctx.json(Map.of("message", "Invalid username"));
        }
    }

}
