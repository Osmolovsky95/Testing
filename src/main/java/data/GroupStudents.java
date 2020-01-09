package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupStudents {

    private Set<Student> students=new HashSet<>();
    private  Set <Administrator> administrators=new HashSet<>();

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

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Administrator> getAdministrators() {
        return administrators;
    }
}


