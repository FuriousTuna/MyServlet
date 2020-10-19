package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/update")
public class ServletUpdate extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();

        model.getFromList().get(id).setName(name);
        model.getFromList().get(id).setSurname(surname);
        model.getFromList().get(id).setSalary(salary);

        resp.setContentType("application/json;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        pw.print(gson.toJson(model.getFromList().get(id)));
    }
}
