package springboot01.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 大数据量的插入---> 测试100万条数据
 * 方法1、正常插入
 * 方法2、事务提交
 * 方法3、批量Batch提交
 */
public class DAOTest {

    /**
     * 一、普通方式
     * 时间:100万条:150034ms
     */
    @Test
    public void test1() {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement stmt = null;
        long t1 = System.currentTimeMillis();
        try {
            stmt = conn.prepareStatement("insert into test_week7 values(?,?,?,?,?)");
            for (int i = 0; i < 1000000; i++) {
                stmt.setInt(1, i);
                stmt.setFloat(2, i);
                stmt.setString(3, i + "");
                stmt.setInt(4, i);
                stmt.setInt(5, i);
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(stmt, conn);
        }
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1));
    }

    /**
     * 二、事务提交 :
     * 时间:100万条:13427ms
     */
    @Test
    public void test2() {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement stmt = null;
        long t1 = System.currentTimeMillis();
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("insert into test_week7 values(?,?,?,?,?)");
            for (int i = 0; i < 1000000; i++) {
                stmt.setInt(1, i);
                stmt.setFloat(2, i);
                stmt.setString(3, i + "");
                stmt.setInt(4, i);
                stmt.setInt(5, i);
                stmt.execute();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release( stmt, conn);
        }
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1));
    }

    /**
     * 三、批处理：
     * 时间 : 100万条:11054ms
     */
    @Test
    public void test3() {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement stmt = null;
        long t1 = System.currentTimeMillis();
        try {
            stmt = conn.prepareStatement("insert into test_week7 values(?,?,?,?,?)");
            for (int i = 0; i < 1000000; i++) {
                stmt.setInt(1, i);
                stmt.setFloat(2, i);
                stmt.setString(3, i + "");
                stmt.setInt(4, i);
                stmt.setInt(5, i);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(stmt, conn);
        }
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1));
    }

}