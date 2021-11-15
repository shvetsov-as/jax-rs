/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import rws.ReturnData;

/**
 *
 * @author User
 */
@WebServlet(name = "DemoRS", urlPatterns = {"/DemoRS"})
public class DemoRS extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String button = request.getParameter("showDir");
        String directory = request.getParameter("directory");

        if (directory.trim().isEmpty() || directory == null) {
            directory = "HOME";
        }

        Client client = ClientBuilder.newClient();//dostup k API clienta
        String result = null;
        String fragment = null;
        boolean flag = false;
        //
        //String flagReg = request.getParameter("fragment");
        //System.out.println("###############Test flag " + flagReg);
//
        if (button != null) {
            System.out.println(" 'show files' variant for " + directory);
            String link = "http://localhost:8080/J210REST_lab3_test_1/webresources/dir/";
            result = client.target(link).// ykazivaem link na nashy slujby
                    path("{directory}").// obrashaemsia k clienty i govorim chto hotim 
                    // otpravitb parametr "directory" (DirResource @Path("{directory}")) 
                    //v pyti zaprosa
                    resolveTemplate("directory", directory).// prisvoitb znachenie dlia "directory"
                    //to chto izvlekli iz requesta
                    request().get(String.class);//delaem zapros i ykazivaem tip otveta 
            //kotoriy hotim polychitb

            request.setAttribute("response", " 'show files' variant for " + directory + " Result: " + result);

        } else {
            fragment = request.getParameter("fragment");
            flag = (request.getParameter("regexp") != null && !request.getParameter("regexp").trim().isEmpty());

                System.out.println(" 'find files' by " + fragment + " in " + directory);
                String link = "http://localhost:8080/J210REST_lab3_test_1/webresources/dir/find";
                result = client.
                        target(link).
                        path("{directory}").
                        resolveTemplate("directory", directory).
                        queryParam("file", fragment).//dobavliaem parametr zaprosa
                        queryParam("flag", flag).
                        request().get(String.class);
            

            request.setAttribute("response", " 'find files' by " + fragment + " in " + directory + " regexp: " + flag + " Result: " + result);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);

//        ReturnData rd = client.
//                        target("http://localhost:8080/J210REST_lab3/webresources/dir/").
//                            request().get(ReturnData.class);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DemoRS</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DemoRS at " + request.getContextPath() + "</h1>");

            //out.println("<h1>Call file = " + fragment + ", flag = " + flag + "</h1>");
            //out.println("<h1>Call result = " + result + "</h1>");
            out.println("<h1>####Return object check ####</h1>");
            //out.println("<h1>" + rd.toString() + "</h1>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
