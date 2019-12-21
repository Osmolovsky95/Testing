package Data;

import java.util.ArrayList;
import java.util.List;

public class GroupStudents {
    private String groupName;
    private List<Student> students=new ArrayList<>();

    private static volatile GroupStudents instance;

    public static GroupStudents getInstance() {
        GroupStudents localInstance = instance;
        if (localInstance == null) {
            synchronized (GroupStudents.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GroupStudents();
                }
            }
        }
        return localInstance;
    }

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