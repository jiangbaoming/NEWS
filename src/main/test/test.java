import cn.kejia.news.dao.UserDao;
import cn.kejia.news.dao.impl.UserDaoImpl;
import cn.kejia.news.model.User;
import org.junit.Test;

import java.util.Date;

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
        User user= new User();
        user.setCreator("管理员");
        user.setCreateDate(new Date());
        user.setModifier("管理员");
        user.setModifyDate(new Date());
        user.setPassword("123456");
        user.setRole(0);
        for (int i=10;i<20;i++){
            user.setUserCode("TEST-"+i);
            user.setUserName("test--"+i);
            user.setPhone("1358888888"+i);
            user.setOid(i);
            userDao.add(user);
            System.out.println("添加>>"+(i+1));
        }
    }
}
