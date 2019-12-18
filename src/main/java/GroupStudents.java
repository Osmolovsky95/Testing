import java.util.ArrayList;
import java.util.List;

public class GroupStudents {
    private String groupName;
    private List<Student> students=new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }
}
