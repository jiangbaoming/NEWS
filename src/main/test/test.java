import cn.kejia.news.dao.UserDao;
import cn.kejia.news.dao.impl.UserDaoImpl;
import org.junit.Test;

/**
 * @Author: 江宝明
 * @Description:
 * @Date:2019/05/12
 * @Modified By：
 */
public class test {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void test1(){
        System.out.println(userDao.getUserByUserCode("a"));
    }
}
