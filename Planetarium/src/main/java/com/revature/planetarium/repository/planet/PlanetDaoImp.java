package com.revature.planetarium.repository.planet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.utility.DatabaseConnector;

import javax.imageio.ImageIO;

public class PlanetDaoImp implements PlanetDao {

    @Override
    public Optional<Planet> createPlanet(Planet planet) {
        // Order to throw exceptions
        // 1. Planet name
        // 2. Image type

        // Check planet does not exist in db


        String planetName = planet.getPlanetName();
        Optional<Planet> existingPlanet = readPlanet(planetName);
        if (existingPlanet.isPresent()){
            // planet already exists throw exception
            throw new PlanetFail("Invalid planet name");
        }

        // Verify name is valid

        String validCharacters ="^[a-zA-Z0-9 _-]+$";
        if (planetName.isEmpty() || planetName.length() > 30
            || !planetName.matches(validCharacters)){
            throw new PlanetFail("Invalid planet name");
        }
        // Check image type


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


        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO planets (name, ownerId, image) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, planet.getPlanetName());
            stmt.setInt(2, planet.getOwnerId());
            stmt.setBytes(3, planet.imageDataAsByteArray());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()) {
                    int newPlanetId = rs.getInt(1);
                    planet.setPlanetId(newPlanetId);
                    return Optional.of(planet);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
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
    public Optional<Planet> readPlanet(String name) {
        try (Connection conn = DatabaseConnector.getConnection()){
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets WHERE name = ?")){
                stmt.setString(1, name);
                try (ResultSet rs = stmt.executeQuery()){
                    if (rs.next()) {
                        Planet planet = new Planet();
                        planet.setPlanetId(rs.getInt("id"));
                        planet.setPlanetName(rs.getString("name"));
                        planet.setOwnerId(rs.getInt("ownerId"));
                        return Optional.of(planet);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Planet> readAllPlanets() {
        List<Planet> planets = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets");
             ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Planet planet = new Planet();
                    planet.setPlanetId(rs.getInt("id"));
                    planet.setPlanetName(rs.getString("name"));
                    planet.setOwnerId(rs.getInt("ownerId"));
                    if(rs.getBytes("image") != null){
                        byte[] imageDataAsBytes = rs.getBytes("image");
                        String imageDataBase64 = Base64.getEncoder().encodeToString(imageDataAsBytes);
                        planet.setImageData(imageDataBase64);
                    }
                    planets.add(planet);
                }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return planets;
    }

    @Override
    public List<Planet> readPlanetsByOwner(int ownerId) {
        List<Planet> planets = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets WHERE ownerId = ?")) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Planet planet = new Planet();
                    planet.setPlanetId(rs.getInt("id"));
                    planet.setPlanetName(rs.getString("name"));
                    planet.setOwnerId(rs.getInt("ownerId"));
                    if(rs.getBytes("image") != null){
                        byte[] imageDataAsBytes = rs.getBytes("image");
                        String imageDataBase64 = Base64.getEncoder().encodeToString(imageDataAsBytes);
                        planet.setImageData(imageDataBase64);
                    }
                    planets.add(planet);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return planets;
    }

    @Override
    public Optional<Planet> updatePlanet(Planet planet) {
        Optional<Planet> existingPlanet = readPlanet(planet.getPlanetId());
        if(existingPlanet.isEmpty()){
            throw new PlanetFail("Planet not in database");
        }

        String planetName = planet.getPlanetName();
        existingPlanet = readPlanet(planetName);
        if (existingPlanet.isPresent() && planet.getPlanetId() != existingPlanet.get().getPlanetId()){
            // planet already exists throw exception
            throw new PlanetFail("Invalid planet name");
        }

        String validCharacters ="^[a-zA-Z0-9 _-]+$";
        if (planetName.isEmpty() || planetName.length() > 30
                || !planetName.matches(validCharacters)){
            throw new PlanetFail("Invalid planet name");
        }

        // Check planet id is valid
        if(!checkOwnerExists(planet.getOwnerId())){
            throw new PlanetFail("Invalid owner id");
        }

        // Check image type
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

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE planets SET name = ?, ownerId = ?, image = ? WHERE id = ?")) {
            stmt.setString(1, planet.getPlanetName());
            stmt.setInt(2, planet.getOwnerId());
            stmt.setBytes(3, planet.imageDataAsByteArray());
            stmt.setInt(4, planet.getPlanetId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? Optional.of(planet) : Optional.empty();
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
    }


    @Override
    public boolean deletePlanet(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM planets WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted < 1) {
                // Nothing was deleted need to throw MoonFail
                throw new PlanetFail("Invalid planet name");
            }
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
    }

    @Override
    public boolean deletePlanet(String name) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM planets WHERE name = ?")) {
            stmt.setString(1, name);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted < 1) {
                // Nothing was deleted need to throw MoonFail
                throw new PlanetFail("Invalid planet name");
            }
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
    }

    @Override
    public boolean checkOwnerExists(int ownerId) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT FROM users WHERE id = ?")) {
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
    }

}
