package servlets;

import Data.GroupStudents;
import Data.TestingDAO;
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

    // TODO: 31.12.2019 Навести порядлк 
        @Override

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            GroupStudents myGroup=GroupStudents.getInstance();
           //    resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            String name = req.getParameter("name");
            String password= req.getParameter("pass");
            //Получаем json
           /* InputStreamReader inputStreamReader=new InputStreamReader(req.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            String str;
            while ((str=bufferedReader.readLine())!=null){
               stringBuffer.append(str);
           }
             System.out.println(stringBuffer.toString());
             JSONObject jsonObject=new JSONObject(stringBuffer.toString());
             String bodyName=jsonObject.getString("name");
             String bodyPass=jsonObject.getString("pass");*/

            try {
                TestingDAO.insertStudent(name,password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            req.setAttribute("userName", name);
            doGet(req, resp);
        }
    }




