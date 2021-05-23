import com.demo.bean.User;
import com.demo.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //添加课程
    @Test
    public void addCourse() {
        User user = new User();
        user.setId(1);
        user.setName("cdy");
        userMapper.insert(user);
    }

}
