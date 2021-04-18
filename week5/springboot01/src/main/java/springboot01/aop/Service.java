package springboot01.aop;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot01.bean.Student;

@Component
public class Service {

    private Student student;

    @Autowired
    Service(Student student) {
        this.student = student;
    }

    @Log
    public String getStudentName() {
        System.out.println("student: " + student.getName());
        return student.getName();
    }

    public static void main(String[] args) throws Exception {
        Service service = new ByteBuddy()
                .subclass(Service.class)
                .method(ElementMatchers.any())
                .intercept(Advice.to(LoggerAdvisor.class))
                .make()
                .load(Service.class.getClassLoader())
                .getLoaded()
                .newInstance();
        service.getStudentName();
    }
}