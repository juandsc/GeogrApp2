package info.sqlite.helper;

public class RockQuality {
    private int id;
    private String name;
    private String description;
    private String created_At;
    private float q1;
    private float q2;
    private String quality;
    private int geograficArea;

    public RockQuality(){}

    public RockQuality(String name, String description, String created_At, int geograficArea) {
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

    public float getQ1() {
        return q1;
    }

    public void setQ1(float q1) {
        this.q1 = q1;
    }

    public float getQ2() {
        return q2;
    }

    public void setQ2(float q2) {
        this.q2 = q2;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
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
