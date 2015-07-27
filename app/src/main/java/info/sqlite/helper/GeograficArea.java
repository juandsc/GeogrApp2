package info.sqlite.helper;

public class GeograficArea {
    private int id;
    private String name;
    private String description;
    private String created_at;

    public GeograficArea(){ }

    public GeograficArea(String name, String description, String created_at){
        this.name = name;
        this.description = description;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
