import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.ApplicationContext;
public class GetApplicationContext {

   public static ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/main/resources/spring/context.xml");

}
