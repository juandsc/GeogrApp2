package info.sqlite.helper;

public class CompassMeasure {
    private int id;
    private String name;
    private String description;
    private String created_At;
    private float orientation;
    private float level;
    private double latitude;
    private double longitude;
    private int geograficArea;

    public CompassMeasure(){}

    public CompassMeasure(String name, String description, String created_At, int geograficArea) {
        this.name = name;
        this.description = description;
        this.created_At = created_At;
        this.geograficArea = geograficArea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getOrientation() {
        return orientation;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getGeograficArea() {
        return geograficArea;
    }

    public void setGeograficArea(int geograficArea) {
        this.geograficArea = geograficArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_At() {
        return created_At;
    }

    public void setCreated_At(String created_At) {
        this.created_At = created_At;
    }
}
