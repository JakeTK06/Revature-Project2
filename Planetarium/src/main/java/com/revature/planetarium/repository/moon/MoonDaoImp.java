package com.revature.planetarium.repository.moon;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.utility.DatabaseConnector;

import javax.imageio.ImageIO;

public class MoonDaoImp implements MoonDao {

    private PlanetDao planetDao;

    @Override
    public Optional<Moon> createMoon(Moon moon) {

        // Order to throw exceptions
        // 1. Planet name
        // 2. Owner id
        // 3. Image type

        // Check planet does not exist in db


        String moonName = moon.getMoonName();
        Optional<Moon> existingMoon = readMoon(moonName);
        if (existingMoon.isPresent()){
            // planet already exists throw exception
            throw new MoonFail("Invalid moon name");
        }

        // Verify name is valid

        String validCharacters ="^[a-zA-Z0-9 _-]+$";
        if (moonName.isEmpty() || moonName.length() > 30
                || !moonName.matches(validCharacters)){
            throw new MoonFail("Invalid moon name");
        }

        // Check planet id is valid
        try {
            if (readPlanet(moon.getOwnerId()).isEmpty()){
                throw new MoonFail("Invalid planet ID");
            }
        } catch (PlanetFail e){
            throw new MoonFail("Invalid planet ID");
        }

        // Check image type
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

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO moons (name, myPlanetId, image) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, moon.getMoonName());
            stmt.setInt(2, moon.getOwnerId());
            stmt.setBytes(3, moon.imageDataAsByteArray());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()) {
                    int newMoonId = rs.getInt(1);
                    moon.setMoonId(newMoonId);
                    return Optional.of(moon);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    // Helper method to determine the format of the image
    private String getFormatName(ByteArrayInputStream bais) throws IOException {
        bais.reset(); // Reset the stream to the beginning
        // Use ImageIO to get image format
        return ImageIO.getImageReaders(ImageIO.createImageInputStream(bais))
                .next().getFormatName();
    }

    @Override
    public Optional<Planet> readPlanet(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets WHERE id = ?")){
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    Planet planet = new Planet();
                    planet.setPlanetId(rs.getInt("id"));
                    planet.setPlanetName(rs.getString("name"));
                    planet.setOwnerId(rs.getInt("ownerId"));
                    return Optional.of(planet);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Moon> readMoon(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                return Optional.of(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Moon> readMoon(String name) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE name = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                return Optional.of(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Moon> readAllMoons() {
        List<Moon> moons = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                moons.add(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return moons;
    }

    @Override
    public List<Moon> readMoonsByPlanet(int planetId) {
        List<Moon> moons = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE myPlanetId = ?")) {
            stmt.setInt(1, planetId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null){
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                moons.add(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return moons;
    }

    @Override
    public Optional<Moon> updateMoon(Moon moon) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE moons SET name = ?, myPlanetId = ? WHERE id = ?")) {
            stmt.setString(1, moon.getMoonName());
            stmt.setInt(2, moon.getOwnerId());
            stmt.setInt(3, moon.getMoonId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? Optional.of(moon) : Optional.empty();
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
    }

    @Override
    public boolean deleteMoon(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM moons WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted < 1) {
                // Nothing was deleted need to throw MoonFail
                throw new MoonFail("Invalid moon name");
            }
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
    }

    @Override
    public boolean deleteMoon(String name) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM moons WHERE name = ?")) {
            stmt.setString(1, name);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted < 1) {
                // Nothing was deleted need to throw MoonFail
                throw new MoonFail("Invalid moon name");
            }
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
    }
    
}
