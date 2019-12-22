package Data;

public class Administrator {
    public static int count=0;
    private String name;
    private String password;
    private int id;

    public Administrator(String name, String password) {
        this.name = name;
        this.password = password;
        this.id=count;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
