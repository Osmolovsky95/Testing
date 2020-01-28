package test;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.ApplicationContext;


public class Context {

   private static volatile ApplicationContext applicationContext;

   public static ApplicationContext getInstance() {
      ApplicationContext localInstance = applicationContext;
      if (localInstance == null) {
         synchronized (Context.class) {
            localInstance = applicationContext;
            if (localInstance == null) {
               applicationContext = localInstance = new FileSystemXmlApplicationContext("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\src\\main\\resources\\spring\\context.xml");
            }
         }
      }
      return localInstance;
   }
}


