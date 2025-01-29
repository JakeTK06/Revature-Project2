package com.revature.planetarium.service.planet;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.planet.PlanetDao;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PlanetServiceImp<T> implements PlanetService<T> {

    private PlanetDao planetDao;

    public PlanetServiceImp(PlanetDao planetDao) {
        this.planetDao = planetDao;
    }

    @Override
    public boolean createPlanet(Planet planet) {
        String accepted_characters = "^[A-Za-z0-9 _-]+$";
        if (planet.getPlanetName().length() < 1 || planet.getPlanetName().length() > 30) {
            throw new PlanetFail("Invalid planet name");
        }
        if (!planet.getPlanetName().matches(accepted_characters)) {
            throw new PlanetFail("Invalid planet name");
        }
        Optional<Planet> existingPlanet = planetDao.readPlanet(planet.getPlanetName());
        if (existingPlanet.isPresent()) {
            throw new PlanetFail("Invalid planet name");
        }

        try {
            if (planet.getImageData() != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(planet.imageDataAsByteArray());
                String format = getFormatName(bais);
                if (!("jpeg".equalsIgnoreCase(format) || "png".equalsIgnoreCase(format))) {
                    throw new PlanetFail("Invalid file type");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Optional<Planet> createdPlanet = planetDao.createPlanet(planet);
        if (createdPlanet.isPresent()) {
            return true;
        } else {
            throw new PlanetFail("Could not create planet");
        }
    }

    @Override
    public Planet selectPlanet(T idOrName) {
        Optional<Planet> planet;
        if (idOrName instanceof Integer) {
            planet = planetDao.readPlanet((int) idOrName);
        } else if (idOrName instanceof String) {
            planet = planetDao.readPlanet((String) idOrName);
        } else {
            throw new PlanetFail("identifier must be an Integer or String");
        }
        if (planet.isPresent()) {
            return planet.get();
        } else {
            throw new PlanetFail("Planet not found");
        }
    }

    @Override
    public List<Planet> selectAllPlanets() {
        return planetDao.readAllPlanets();
    }

    @Override
    public List<Planet> selectByOwner(int ownerId) {
        return planetDao.readPlanetsByOwner(ownerId);
    }

    @Override
    public Planet updatePlanet(Planet planet) {
        // Things to check for
        // 1) planet id in db
        // 2) new planet name
        //      - doesnt exist in database at a different id
        //      - meets character constraints
        // 3) new owner id
        // 4) image type

        // planet id in db
        Optional<Planet> existingPlanet = planetDao.readPlanet(planet.getPlanetId());
        if (existingPlanet.isEmpty()) {
            throw new PlanetFail("Planet not in database");
        }

        // planet doesnt exist in db at a different id
        Optional<Planet> planetWithSameName = planetDao.readPlanet(planet.getPlanetName());
        if (planetWithSameName.isPresent() && planetWithSameName.get().getPlanetId() != planet.getPlanetId()) {
            throw new PlanetFail("Invalid planet name");
        }
        // - Moon name meets character constraints
        String validCharacters ="^[a-zA-Z0-9 _-]+$";
        if (planet.getPlanetName().isEmpty() || planet.getPlanetName().length() > 30
                || !planet.getPlanetName().matches(validCharacters)){
            throw new PlanetFail("Invalid planet name");
        }

        // check owner id
        if(!planetDao.checkOwnerExists(planet.getOwnerId())){
            throw new PlanetFail("Invalid owner id");
        }
        // check image type
        try {
            if (planet.getImageData() != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(planet.imageDataAsByteArray());
                String format = getFormatName(bais);
                if (!("jpeg".equalsIgnoreCase(format) || "png".equalsIgnoreCase(format))) {
                    throw new PlanetFail("Invalid file type");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Optional<Planet> updatedPlanet = planetDao.updatePlanet(planet);
        if (updatedPlanet.isPresent()) {
            return updatedPlanet.get();
        } else {
            throw new PlanetFail("Planet update failed, please try again");
        }
    }

    @Override
    public boolean deletePlanet(T idOrName) {
        boolean deleted;

        if (idOrName instanceof Integer) {
            deleted = planetDao.deletePlanet((int) idOrName);
        }

        else if (idOrName instanceof String) {
            String name = (String) idOrName;
            if (planetDao.readPlanet(name).isEmpty()) {
                throw new PlanetFail("Invalid planet name");
            } else {
                deleted = planetDao.deletePlanet((String) idOrName);
            }

        }

        else {
            throw new PlanetFail("identifier must be an Integer or String");
        }
        if (deleted) {
            return true;
        } else {
            throw new PlanetFail("Invalid planet name");
        }
    }

    @Override
    public boolean checkName(String planetName) {
        try {
            Optional<Planet> foundPlanet = planetDao.readPlanet(planetName);
            return foundPlanet.isPresent();
        } catch (PlanetFail e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to determine the format of the image
    private String getFormatName(ByteArrayInputStream bais) throws IOException {
        bais.reset(); // Reset the stream to the beginning
        // Use ImageIO to get image format
        return ImageIO.getImageReaders(ImageIO.createImageInputStream(bais))
                .next().getFormatName();
    }

}
