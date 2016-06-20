package com.GradingSystem.Validators;

import com.GradingSystem.Services.TeacherBean;
import com.GradingSystem.model.Gender;
import com.GradingSystem.model.Teacher;

import javax.ejb.EJB;
import javax.management.Attribute;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Didoy on 6/17/2016.
 */
public class Validator {

    private  static TeacherBean teacherBean;

    public static HttpServletRequest validateTeacherRegistration(Teacher teacher, String cPassword, HttpServletRequest request) {


        try {
            Context context = new InitialContext();
            Object teacherobject =  context.lookup("java:global/Recordie2/TeacherEJB!com.GradingSystem.Services.TeacherBean");
             teacherBean = (TeacherBean) teacherobject;

        } catch (NamingException e) {
            e.printStackTrace();
        }

        String name = teacher.getName();
        String email = teacher.getEmail();
        String password = teacher.getPassword();
        String Cpassword = cPassword;

        // empty fields validations
        if (email.equals("") || email.equals(null)) {
            request.setAttribute("email_empty", true);
            request.setAttribute("error", true);
        }else {
            if (!email.contains("@") || !email.contains(".")) {
                request.setAttribute("invalid_email", true);
                request.setAttribute("error", true);
                request.setAttribute("email", teacher.getEmail());

            } else {
                Teacher t = teacherBean.getTeacherByEmail(email);
                if (t != null){
                    if (email.equals(t.getEmail().trim()))
                    {
                        request.setAttribute("error", true);
                        request.setAttribute("email_taken", true);
                    }
                }
                request.setAttribute("email", email);
            }
        }
        if (Cpassword.equals("") || Cpassword.equals(null)) {
            request.setAttribute("error", true);
            request.setAttribute("password_empty", true);
        }

        if (name.equals("") || name.equals(null)) {
            request.setAttribute("name_empty", true);
            request.setAttribute("error", true);
        } else {
            String nameRegex = "^[A-Za-z .]{1,50}";
            if (!name.matches(nameRegex)) {
                request.setAttribute("invalid_name", true);
                request.setAttribute("error", true);
            }
            request.setAttribute("name", teacher.getName());
        }

        if (Cpassword.equals("") || Cpassword.equals(null)) {
            request.setAttribute("cpassword_empty", true);
            request.setAttribute("error", true);
        }

        if (password.equals("") || password.equals(null)) {
            request.setAttribute("password_empty", true);
            request.setAttribute("error", true);
        } else {
            String passRegex = "^[A-Za-z0-9]{6,20}";
            if (!password.matches(passRegex) && !cPassword.matches(passRegex)) {
                request.setAttribute("invalid_password", true);
                request.setAttribute("error", true);
            } else {
                if (!password.equals(Cpassword)) {
                    request.setAttribute("error", true);
                    request.setAttribute("password_not_match", true);
                }
            }
            request.setAttribute("password", teacher.getPassword());
            request.setAttribute("cpassword", Cpassword);
        }

        // no error detected, trim the values ands set teacher as attribute to be used by Register servlet
        if (!(Boolean) request.getAttribute("error")) {
            teacher.setName(teacher.getName().trim());
            teacher.setEmail(teacher.getEmail().trim());
            teacher.setPassword(teacher.getPassword().trim());
            teacher.setGender(teacher.getGender());

            request.setAttribute("teacher", teacher);
        }

        return request;
    }

}
