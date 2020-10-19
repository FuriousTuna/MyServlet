package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calc")
public class ServletCalc extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        double a = jsonObject.get("a").getAsDouble();
        double b = jsonObject.get("b").getAsDouble();
        String symbol = jsonObject.get("math").getAsString();
        double result = 0;
        JsonObject jsonResult = new JsonObject();

        switch (symbol) {
            case "+" :
                result = a + b;
                jsonResult.addProperty("result", result);
                break;
            case "-" :
                result = a - b;
                jsonResult.addProperty("result", result);
                break;
            case "*" :
                result = a * b;
                jsonResult.addProperty("result", result);
                break;
            case "/" :
                result = a / b;
                jsonResult.addProperty("result", result);
                break;
            default:
                jsonResult.addProperty("error", "Illegal arithmetic operation!!!");
                break;
        }

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.print(gson.toJson(jsonResult));
    }
}
