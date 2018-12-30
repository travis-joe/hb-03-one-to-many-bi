package com.travis.hibernate.demo;

import com.travis.hibernate.demo.entity.Course;
import com.travis.hibernate.demo.entity.Instructor;
import com.travis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession(); factory) {
            Instructor instructor = new Instructor("travis", "joe", "weelgod@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com", "luv2code");
            Course course = new Course("sex position");
            Course course2 = new Course("play sluts");
            instructor.setInstructorDetail(instructorDetail);
            instructor.add(course);
            instructor.add(course2);
            session.beginTransaction();
            session.save(course);
            session.save(course2);
            session.save(instructor);
            session.getTransaction().commit();
        }
    }
}
