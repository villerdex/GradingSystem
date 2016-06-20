package com.GradingSystem.Controller;

import com.GradingSystem.Services.TeacherBean;
import com.GradingSystem.Validators.Validator;
import com.GradingSystem.model.Gender;
import com.GradingSystem.model.Teacher;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Didoy on 6/17/2016.
 */
@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {

    @EJB(beanName = "TeacherEJB")
    private TeacherBean teacherBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        System.out.println("Do post was called");

        // clear teacher request attributes
        clearTeacherRequestAttributes(request);

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String Cpassword = request.getParameter("confirmpassword");
        String sex = request.getParameter("gender");
        Gender gender = Gender.valueOf(sex);

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setGender(gender);
        teacher.setPassword(password);

        //validate fields and return attributes as String
        request = Validator.validateTeacherRegistration(teacher, Cpassword, request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/index.jsp");

        if ((Boolean) request.getAttribute("error")) {
            requestDispatcher.forward(request, response);
            session.setAttribute("clear", false);
            System.out.println("Clear false");

        } else {
            teacher = (Teacher) request.getAttribute("teacher");
            clearTeacherRequestAttributes(request);            // clear teacher request attributes
            // redirect to new page
            session.setAttribute("clear", true);
            System.out.println("Clear True");
            teacherBean.RegisterTeacher(teacher);
            requestDispatcher.forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void clearTeacherRequestAttributes(HttpServletRequest request){
        // teacher property attributes
        request.setAttribute("name", "");
        request.setAttribute("email", "");
        request.setAttribute("password", "");
        request.setAttribute("cpassword", "");
        request.setAttribute("gender", "");

        // set request default attributes
        request.setAttribute("error", false);
        request.setAttribute("email_empty", false);
        request.setAttribute("name_empty", false);
        request.setAttribute("invalid_name", false);
        request.setAttribute("invalid_email", false);
        request.setAttribute("email_taken", false);
        request.setAttribute("invalid_password", false);
        request.setAttribute("password_empty", false);
        request.setAttribute("password_not_match", false);

    }
}
