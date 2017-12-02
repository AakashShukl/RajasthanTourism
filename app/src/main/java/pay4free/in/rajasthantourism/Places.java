package pay4free.in.rajasthantourism;


/**
 * Created by AAKASH on 10-10-2017.
 */

public class Places {
    private String Name;
    private String Image;
    private String Description;

    public Places()
    {

    }

    public Places(String name, String image, String description, String price, String discount, String menuId) {
        Name = name;
        Image = image;
        Description = description;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
