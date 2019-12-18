import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {

        private static Model instance = new Model();

        private List<Student> model;

        public static Model getInstance() {
            return instance;
        }

        private Model() {
            model = new ArrayList<Student>();
        }

        public void add(Student student) {
            model.add(student);
        }

        public List<Object> list() {
            return model.stream()
                    .map(Student::getName)
                    .collect(Collectors.toList());
        }
    }

