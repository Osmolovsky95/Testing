package servlets;

import data.GroupStudents;
import data.StudentDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;


public class AddServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setCharacterEncoding("UTF-8");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
            requestDispatcher.forward(req, resp);
        }

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            GroupStudents myGroup=GroupStudents.getInstance();
            resp.setContentType("text/html;charset=UTF-8");
            String name = req.getParameter("name");
            String password= req.getParameter("pass");

            //Получаем json
          /* InputStreamReader inputStreamReader=new InputStreamReader(req.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

        //Чтение ткста     BufferedReader bufferedReader = req.getReader();

            StringBuffer stringBuffer=new StringBuffer();
            String str;
            while ((str=bufferedReader.readLine())!=null){
               stringBuffer.append(str);
           }
             System.out.println(stringBuffer.toString());
            // JSONObject jsonObject=new JSONObject(stringBuffer.toString());
           //  String bodyName=jsonObject.getString("name");
           //  String bodyPass=jsonObject.getString("pass");
            System.out.println(resp.getStatus());*/

           try {
                StudentDAO.insertStudent(name,password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            req.setAttribute("userName", name);
            doGet(req, resp);
        }
    }




