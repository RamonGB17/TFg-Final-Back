package org.example.triviadexbackend.dto;

public class CharacterDTO {

    private String name;
    private String gender;
    private String species;
    private String year;
    private String imageFilename;
    private Long franchiseId;

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getImageFilename() { return imageFilename; }
    public void setImageFilename(String imageFilename) { this.imageFilename = imageFilename; }

    public Long getFranchiseId() { return franchiseId; }
    public void setFranchiseId(Long franchiseId) { this.franchiseId = franchiseId; }
}
