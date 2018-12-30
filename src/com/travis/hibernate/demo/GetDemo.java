package com.travis.hibernate.demo;

import com.travis.hibernate.demo.entity.Course;
import com.travis.hibernate.demo.entity.Instructor;
import com.travis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession(); factory) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, 4);
            System.out.println(instructor.getCourses());
            session.getTransaction().commit();
        }
    }
}
