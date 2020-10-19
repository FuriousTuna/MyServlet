package ru.appline;

import com.google.gson.*;
import ru.appline.logic.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = resp.getWriter();
//
//        int id = Integer.parseInt(req.getParameter("id"));
//
//        if (id == 0) {
//            pw.print("<html>" +
//                     "<h3>Доступные пользователи:<h3><br>" +
//                     "ID пользователя: " +
//                     "<ul>");
//
//            for (Map.Entry<Integer, User> user: model.getFromList().entrySet()) {
//                pw.print("<li>" + user.getKey() + "</li>" +
//                         "<ul>" +
//                         "<li>Имя: " + user.getValue().getName() + "</li>" +
//                         "<li>Фамилия: " + user.getValue().getSurname() + "</li>" +
//                         "<li>Зарплата: " + user.getValue().getSalary() + "</li>" +
//                         "</ul>");
//            }
//            pw.print("</ul>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        } else if (id >0) {
//            if (id > model.getFromList().size()) {
//                pw.print("<html>" +
//                        "<h3>Такого пользователя нет<h3>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            } else {
//                pw.print("<html>" +
//                        "<h3>Запрошенный пользователь:<h3><br>" +
//                        "Имя: " + model.getFromList().get(id).getName() + "<br>" +
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br>" +
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            }
//        } else {
//            pw.print("<html>" +
//                    "<h3>ID должен быть больше нуля!<h3>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        }
//
//
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json;charset=utf-8");

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

        int id = jsonObject.get("id").getAsInt();

        if (id == 0) {
            pw.print(gson.toJson(model.getFromList()));
        } else if (id > 0) {
            pw.print(gson.toJson(model.getFromList().get(id)));
        } else {
            JsonObject jsonError = new JsonObject();
            jsonError.addProperty("error", "Illegal number for id!!!");
            pw.print(gson.toJson(jsonError));
        }

    }
}
