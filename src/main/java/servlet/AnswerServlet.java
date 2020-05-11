package servlet;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "MyServlet", urlPatterns = { "/answer" })

public class AnswerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Invalid request</TITLE>");
        out.println("</HEAD>");

        out.println("<BODY>");
        out.println("<CENTER>");
        out.println("<P>Invalid GET request: This service only accepts POST requests</P>");
        out.println("</CENTER>");
        out.println("</BODY>");

        out.println("</HTML>");
        out.flush();

        out.close();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("aplication/json");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST");
        res.setHeader("Access-Control-Allow-Headers", "*");

        PrintWriter out = res.getWriter();

        Map<String, String[]> parameterMap = req.getParameterMap();

        String[] facts = { "guinea pigs must be kept in pairs (at the bare minimum!) or they will be sad and stressed",
                "both animals eat about 1.5-3% of their body weight in hay",
                "they are herbivores and they like eating veggies and fruits like broccoli, spinach and blueberries",
                "they are not related at all -- their name likely came from their squeaky noises that sound similar to pig squeals",
                "they are also closely related to chinchillas, porcupines, and capybaras (bonus fact! capybaras are the worlds largest rodent)" };

        String[] answerKey = { "false", "true", "false", "false", "true" };

        String result;
        String resultValue;
        String choice;
        Map<String, String> retVals = new HashMap<String, String>();
        for (int i = 0; i < 5; i++) {

            // check if answer correct
            // display correct result
            choice = parameterMap.get("selectedOption" + i)[0];
            result = (choice.equals(answerKey[i]) ? "Correct!" : "Incorrect!");

            // add fact
            resultValue = result + " " + facts[i];

            // store as result0: resultValue
            retVals.put("result" + i, resultValue);
        }

        out.print(new Gson().toJson(retVals));
        out.flush();
        out.close();
    }

}
