package com.revature.planetarium.controller;

import java.util.List;
import java.util.Map;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.service.moon.MoonService;

import io.javalin.http.Context;

public class MoonController {

    private MoonService moonService;

    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }

    public void findAll(Context ctx) {
        List<Moon> moons = moonService.selectAllMoons();
        ctx.json(moons);
        ctx.status(200);
    }

    public void findAllByPlanet(Context ctx) {
        int ownerId = Integer.parseInt(ctx.pathParam("planetId"));
        List<Moon> moons = moonService.selectByPlanet(ownerId);
        ctx.json(moons);
        ctx.status(200);
    }

    public void findByIdentifier(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            Moon moon;
            if(identifier.matches("^[0-9]+$")) {
                moon = moonService.selectMoon(Integer.parseInt(identifier));
            } else {
                moon = moonService.selectMoon(identifier);
            }
            ctx.json(moon);
            ctx.status(200);
        } catch (MoonFail e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    }

    public void createMoon(Context ctx) {
        try {
            Moon moon = ctx.bodyAsClass(Moon.class);
            moonService.createMoon(moon);
            ctx.status(201);
        } catch (MoonFail e) {
            ctx.json(Map.of("message", e.getMessage()));
            ctx.status(400);
        }
    }

    public void updateMoon(Context ctx) {
        String moonId= ctx.pathParam("moonId");
        if(moonId == null || moonId.isEmpty()) {
            ctx.status(400);  // Bad Request
            return;
        }
        try {
            Moon newMoon = ctx.bodyAsClass(Moon.class);
            newMoon.setMoonId(Integer.parseInt(moonId));
            Moon updatedMoon = moonService.updateMoon(newMoon);
            ctx.json(updatedMoon);
            ctx.status(201);
        } catch (MoonFail e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }

    }

    public void deleteMoon(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            String responseMessage;
            responseMessage = String.valueOf(moonService.deleteMoon(identifier));
            ctx.json(responseMessage);
            ctx.status(204);
        } catch (MoonFail e) {
            ctx.json(Map.of("message", e.getMessage()));
            ctx.status(404);
        }
    }

    public void checkExistingMoon(Context ctx) {
        String moonName = ctx.queryParam("moonName");

        // Check if the username is provided
        if (moonName == null || moonName.isEmpty()) {
            ctx.status(400);  // Bad Request
            ctx.json(Map.of("message", "moon name is required"));
            return;
        }

        try {
            boolean foundUser = moonService.checkName(moonName);  // Check if the user exists
            ctx.status(200);  // OK status
            ctx.json(foundUser);  // Return the result (true or false)
        } catch (UserFail e) {
            ctx.status(401);  // Unauthorized if user not found
            ctx.json(Map.of("message", "Invalid moon name"));
        }
    }

}
