package com.travis.hibernate.demo;

import com.travis.hibernate.demo.entity.Course;
import com.travis.hibernate.demo.entity.Instructor;
import com.travis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession(); factory) {
            session.beginTransaction();
            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                            "join fetch i.courses " +
                            "where i.id=:theInstructorId",
                    Instructor.class);
            query.setParameter("theInstructorId", 5);
            Instructor instructor = query.getSingleResult();
            System.out.println(instructor);
            session.getTransaction().commit();
            System.out.println(instructor.getCourses());
        }
    }
}
