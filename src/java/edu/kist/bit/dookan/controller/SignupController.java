/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.controller;

import edu.kist.bit.dookan.entity.Userdetail;
import edu.kist.bit.dookan.services.UserdetailJpaController;
import edu.kist.bit.dookan.utils.FileUploadDTO;
import edu.kist.bit.dookan.utils.FileUploadUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shankar xtha
 */
@WebServlet(name = "SignupController", urlPatterns = {"/signup", "/register"})
@MultipartConfig
public class SignupController extends HttpServlet {

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
        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("Dookanemf");
        String location = "login.jsp";

        String path = request.getServletPath();
        switch (path) {

            case "/signup":
                location = "signup.jsp";
                break;
            case "/register":
                UserdetailJpaController userdetailJpaController = new UserdetailJpaController(emf);
                Userdetail user = new Userdetail();

                user.setFirstName(request.getParameter("first_name"));
                user.setLastName(request.getParameter("last_name"));
                user.setEmail(request.getParameter("email"));   
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));
                user.setRole(request.getParameter("role"));
                //System.out.println(user.setRole(request.getParameter("role")));
                BigInteger phone = new BigInteger(request.getParameter("contact"));
                //System.out.println(phone);
                user.setPhoneNum(phone);
                user.setAddress(request.getParameter("address"));
                FileUploadDTO fileUpload = FileUploadUtil.fileUpload(request, response, "photoUpload");
                user.setImage(fileUpload.getFileLocation());
                userdetailJpaController.create(user);

                location = "login.jsp";
                break;
            default:
                location = "signup.jsp";
                break;

        }

        dispatchRequest(request, response, location);
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

    private void dispatchRequest(HttpServletRequest request, HttpServletResponse response, String signupURL) throws ServletException, IOException {
        request.getRequestDispatcher(signupURL).forward(request, response);
    }

}
