package test.java.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TestMyBatis {
	static SqlSessionFactory sqlSessionFactory = null;
	static {
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	}

	public static void main(String[] args) {
		testAdd();
		getUser();
	}

	public static void testAdd() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = new User("lisi", new Integer(25));
			userMapper.insertUser(user);
			sqlSession.commit();// 杩欓噷涓�畾瑕佹彁浜わ紝涓嶇劧鏁版嵁杩涗笉鍘绘暟鎹簱涓�
		} finally {
			sqlSession.close();
		}
	}

	public static void getUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			User user = userMapper.getUser("zhangsan");
			System.out.println("name: " + user.getName() + "|age: "
					+ user.getAge());
		} finally {
			sqlSession.close();
		}
	}
}
