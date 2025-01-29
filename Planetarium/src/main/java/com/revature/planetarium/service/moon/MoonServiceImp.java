package com.revature.planetarium.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.planet.PlanetDao;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MoonServiceImp<T> implements MoonService<T> {
    
    private MoonDao moonDao;

    public MoonServiceImp(MoonDao moonDao, PlanetDao planetDao) {
        this.moonDao = moonDao;
    }


    // Helper method to determine the format of the image
    private String getFormatName(ByteArrayInputStream bais) throws IOException {
        bais.reset(); // Reset the stream to the beginning
        // Use ImageIO to get image format
        return ImageIO.getImageReaders(ImageIO.createImageInputStream(bais))
                .next().getFormatName();
    }

    @Override
    public boolean createMoon(Moon moon) {
        String accepted_characters = "^[A-Za-z0-9 _-]+$";
        if (moon.getMoonName().length() < 1 || moon.getMoonName().length() > 30) {
            throw new MoonFail("Invalid moon name");
        }
        if (!moon.getMoonName().matches(accepted_characters)) {
            throw new MoonFail("Invalid moon name");
        }
        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonName());
        if (existingMoon.isPresent()) {
            throw new MoonFail("Invalid moon name");
        }

        Optional<Planet> existingPlanet = moonDao.readPlanet(moon.getOwnerId());
        if (!existingPlanet.isPresent()) {
            throw new MoonFail("Invalid planet ID");
        }

        try {
            if (moon.getImageData() != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(moon.imageDataAsByteArray());
                String format = getFormatName(bais);
                if (!("jpeg".equalsIgnoreCase(format) || "png".equalsIgnoreCase(format))) {
                    throw new MoonFail("Invalid file type");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        Optional<Moon> newMoon = moonDao.createMoon(moon);
        if (newMoon.isEmpty()) {
            throw new MoonFail("Unable to create moon");
        }
        return true;
    }


    @Override
    public Moon selectMoon(T idOrName) {
        Optional<Moon> moon;
        if (idOrName instanceof Integer) {
            moon = moonDao.readMoon((Integer) idOrName);
        } else if (idOrName instanceof String) {
            moon = moonDao.readMoon((String) idOrName);
        } else {
            throw new MoonFail("Identifier must be an Integer or String");
        }
        if(moon.isPresent()) {
            return moon.get();
        } else {
            throw new MoonFail("Moon not found");
        }
    }

    @Override
    public List<Moon> selectAllMoons() {
        return moonDao.readAllMoons();
    }

    @Override
    public List<Moon> selectByPlanet(int planetId) {
        return moonDao.readMoonsByPlanet(planetId);
    }

    @Override
    public Moon updateMoon(Moon moon) {
        // Order to throw exceptions
        // 1. moon id exists in db
        // 2. new moon name is valid
        //      - doesnt exist in database at a different id
        //      - meets character constraints
        // 2. planet id is valid
        // 3. Image type

        // Check moon id
        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonId());
        if (existingMoon.isEmpty()) {
            throw new MoonFail("Invalid moon ID");
        }

        // Moon name check
        // - Moon name is unique
        Optional<Moon> moonWithSameName = moonDao.readMoon(moon.getMoonName());
        if (moonWithSameName.isPresent() && moonWithSameName.get().getMoonId() != moon.getMoonId()) {
            throw new MoonFail("Invalid moon name");
        }
        // - Moon name meets character constraints
        String validCharacters ="^[a-zA-Z0-9 _-]+$";
        if (moon.getMoonName().isEmpty() || moon.getMoonName().length() > 30
                || !moon.getMoonName().matches(validCharacters)){
            throw new MoonFail("Invalid moon name");
        }

        // Check planet id is valid
        try {
            if (moonDao.readPlanet(moon.getOwnerId()).isEmpty()){
                throw new MoonFail("Invalid planet ID");
            }
        } catch (PlanetFail e){
            throw new MoonFail("Invalid planet ID");
        }
        // Check image type is valid
        try {
            if (moon.getImageData() != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(moon.imageDataAsByteArray());
                String format = getFormatName(bais);
                if (!("jpeg".equalsIgnoreCase(format) || "png".equalsIgnoreCase(format))) {
                    throw new MoonFail("Invalid file type");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // now update
        Optional<Moon> updatedMoon = moonDao.updateMoon(moon);
        if (updatedMoon.isPresent()) {
            return updatedMoon.get();
        } else {
            throw new MoonFail("Moon update failed, please try again");
        }
    }

    @Override
    public boolean deleteMoon(T idOrName) {
        boolean deleted;
        if (idOrName instanceof Integer) {
            deleted = moonDao.deleteMoon((int) idOrName);
        }

        else if (idOrName instanceof String) {
            String name = (String) idOrName;
            if (moonDao.readMoon(name).isEmpty()) {
                throw new MoonFail("Invalid moon name");
            } else {
                deleted = moonDao.deleteMoon((String) idOrName);

            }
        } else {
            throw new PlanetFail("identifier must be an Integer or String");
        }
        if (deleted) {
            return true;
        } else {
            throw new MoonFail("Invalid moon name");
        }
    }

    @Override
    public boolean checkName(String moonName) {
        try {
            Optional<Moon> foundMoon = moonDao.readMoon(moonName);
            return foundMoon.isPresent();
        } catch (MoonFail e) {
            e.printStackTrace();
        }
        return false;
    }

}
