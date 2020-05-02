package servlet;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyServlet", urlPatterns = { "/answer" })

public class AnswerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("    <title>LK Fountains at GMU</title>");
        out.println("    <link rel=stylesheet href=\"SWE432-Assn5-styles.css\" type=\"text/css\">");
        out.println("    <script type=\"text/javascript\" src=\"SWE432-Assn5-script.js\"></script>");
        out.println("</head>");
        out.println("<body>");
        // out.println("    <form method=\"POST\" action=\"https://cs.gmu.edu:8443/offutt/servlet/formHandler\">");
        out.println("    <form method=\"POST\" action=\"https://swe432-assnsix.herokuapp.com/hello\">");
        out.println("        <h1 class=\"title-text\">Innovation Hall Water Fountains</h1>");
        out.println("        <h2>Floor</h2>");
        out.println("        <span>Which floor's fountains are you rating?</span></br>");
        out.println("        <input type=\"radio\" id=\"first\" name=\"floor\" value=\"first\">");
        out.println("        <label for=\"first\">First</label><br>");
        out.println("        <input type=\"radio\" id=\"second\" name=\"floor\" value=\"second\">");
        out.println("        <label for=\"second\">Second</label><br>");
        out.println("        <input type=\"radio\" id=\"third\" name=\"floor\" value=\"third\">");
        out.println("        <label for=\"third\">Third</label><br>");
        out.println("        <input type=\"radio\" id=\"fourth\" name=\"floor\" value=\"fourth\">");
        out.println("        <label for=\"fourth\">Fourth</label><br>");
        out.println("        <h2>Temperature</h2>");
        out.println("        <span>How would you rate the temperature of the water?</span><br>");
        out.println("        <input type=\"radio\" id=\"ice\" name=\"temperature\" value=\"ice\">");
        out.println("        <label for=\"ice\">Ice Cold</label><br>");
        out.println("        <input type=\"radio\" id=\"mostlycold\" name=\"temperature\" value=\"cold\">");
        out.println("        <label for=\"cold\">Mostly Cold</label><br>");
        out.println("        <input type=\"radio\" id=\"roomtemp\" name=\"temperature\" value=\"roomtemp\">");
        out.println("        <label for=\"roomtemp\">Room Temperature</label><br>");
        out.println("        <input type=\"radio\" id=\"warm\" name=\"temperature\" value=\"warm\">");
        out.println("        <label for=\"warm\">Warm</label><br>");
        out.println("        <h2>Stream</h2>");
        out.println("        <span>How would you rate the speed of the stream of water?</span><br>");
        out.println("        <input type=\"radio\" id=\"Fast\" name=\"stream\" value=\"Fast\">");
        out.println("        <label for=\"Fast\">Fast</label><br>");
        out.println("        <input type=\"radio\" id=\"Moderate\" name=\"stream\" value=\"Moderate\">");
        out.println("        <label for=\"Moderate\">Moderate</label><br>");
        out.println("        <input type=\"radio\" id=\"Slow\" name=\"stream\" value=\"Slow\">");
        out.println("        <label for=\"Slow\">Slow</label><br>");
        out.println("        <h2>Location</h2>");
        out.println("        <span>How would you rate how convenient a place this fountain is in?</span><br>");
        out.println("        <input type=\"radio\" id=\"Convenient\" name=\"location\" value=\"Convenient\">");
        out.println("        <label for=\"Convenient\">Convenient</label><br>");
        out.println("        <input type=\"radio\" id=\"Not Convenient\" name=\"location\" value=\"Not Convenient\">");
        out.println("        <label for=\"Not Convenient\">Not Convenient</label><br>");
        out.println("        <input class=\"submit-btn\" type=\"submit\" onclick=\"alertFunc()\">");
        out.println("    </form>");
        out.println(
                "    <p id=\"summary\" onmouseout=\"removeCollabSumm()\" onmouseover=\"showCollabSumm()\">Collaboration Summary: <br></p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        out.flush();

        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // first, set the "content type" header of the response
        response.setContentType("text/html");
        // Get the response's PrintWriter to return text to the client.
        PrintWriter toClient = response.getWriter();

        String para;
        Enumeration paraNames = request.getParameterNames();

        toClient.println("<html>");
        toClient.println("<head>");
        toClient.println("  <title>Generic form handler</title>");
        toClient.println("</head>");

        toClient.println("<body bgcolor=\"#EEEEEE\">");
        toClient.println("");
        toClient.println("<center><h2>Generic form handler</h2></center>");
        toClient.println("<p>");
        toClient.println("The following table lists all parameter names and");
        toClient.println("their values that were submitted from your form.");
        toClient.println("</p>");
        toClient.println("");
        toClient.println("<p>");
        toClient.println("<table cellSpacing=1 cellPadding=1 width=\"75%\" border=1 bgColor=lavender>");
        toClient.println("");
        toClient.println("  <tr bgcolor=\"#FFFFFF\">");
        toClient.println("   <th align=\"center\"><b>Parameter</b></td>");
        toClient.println("   <th align=\"center\"><b>Value</b></td>");
        toClient.println("  </tr>");

        while (paraNames.hasMoreElements()) { // For each parameter name.
            para = (String) paraNames.nextElement();
            if (!para.equalsIgnoreCase("submit")) {
                toClient.println("  <tr>");
                toClient.println("    <td style=\"width: 20%\" width=\"20%\"><b>" + para + "</b></td>");

                String[] values = request.getParameterValues(para);

                if (values != null && !values[0].equals(""))
                    toClient.println("    <td>" + values[0] + "</td></tr>");
                else
                    toClient.println("    <td>&nbsp;</td></tr>");

                for (int i = 1; i < values.length; i++) {
                    if (!values[i].equals("")) {
                        toClient.println("  <tr>");
                        toClient.println("    <td style=\"width: 20%\" width=\"20%\">&nbsp;</td>");
                        toClient.println("    <td>" + values[i] + "</td></tr>");
                    }
                }
            }
        }
        toClient.println("</table>");
        toClient.println("");
        toClient.println("</body>");
        toClient.println("</html>");

        toClient.println("");

        // Close the writer; the response is done.
        toClient.close();
    } // end of doPost()
}
