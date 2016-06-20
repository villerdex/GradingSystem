package com.GradingSystem.Services;

import com.GradingSystem.model.Teacher;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by Didoy on 6/16/2016.
 */
@Stateless(name = "TeacherEJB")
@Local
public class TeacherBean {

    @PersistenceContext(unitName = "GradingSystem")
    private EntityManager em;

    public TeacherBean() {
    }

    public void RegisterTeacher(Teacher teacher){
        em.persist(teacher);
    }

    public Teacher getTeacherByEmail(String email){

        try {
            TypedQuery<Teacher> teacherTypedQuery = em.createNamedQuery("FindByEmail", Teacher.class);
            teacherTypedQuery.setParameter("email", email );

            Teacher t = teacherTypedQuery.getSingleResult();
            return t;

        }catch (NoResultException ex){
            return null;
        }

    }
}
