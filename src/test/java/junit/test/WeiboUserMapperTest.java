package junit.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.weibo.mapper.WeiboUserMapper;
import com.springcloud.weibo.pojo.WeiboUser;

@Transactional
public class WeiboUserMapperTest extends BaseSpringTest {

    @Autowired
    private WeiboUserMapper weiboUserMapper;

    @Test
    public void testCountByExample() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteByExample() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteByPrimaryKey() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsert() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertSelective() {
        WeiboUser weiboUser = new WeiboUser();
        weiboUser.setId("123");
        weiboUser.setNickName("sdjk");
        weiboUser.setPassword("123456");
        int count = weiboUserMapper.insertSelective(weiboUser);
        System.out.println(count);
    }

    @Test
    public void testSelectByExample() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectByPrimaryKey() {
        WeiboUser weiboUser = weiboUserMapper.selectByPrimaryKey("123");
        System.out.println(weiboUser);
    }

    @Test
    public void testSelectByPrimaryKeyAndPassword() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "123");
        map.put("password", "123456");
        WeiboUser weiboUser = weiboUserMapper.selectByPrimaryKeyAndPassword(map);
        System.out.println(weiboUser);
    }

    @Test
    public void testUpdateByExampleSelective() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateByExample() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateByPrimaryKey() {
        fail("Not yet implemented");
    }

}
