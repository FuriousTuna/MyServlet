package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name, surname, salary);
//
//        model.add(counter.getAndIncrement(), user);
//
//        pw.print("<html>" +
//                 "<h3>Пользователь " + name + " " + surname + " с зарплатой = " + salary + " успешно создан.</h3>" +
//                 "<a href=\"addUser.html\">Создать нового пользователя</a><br/>" +
//                 "<a href=\"index.jsp\">Домой</a>" +
//                 "</html>");
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer buf = new StringBuffer();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                buf.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        System.out.println(buf);

        JsonObject jsonObject = gson.fromJson(String.valueOf(buf), JsonObject.class);

        req.setCharacterEncoding("UTF-8");

        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();

        User user = new User(name, surname, salary);

        model.add(counter.getAndIncrement(), user);

        resp.setContentType("application/json;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        pw.print(gson.toJson(model.getFromList()));
    }
}
