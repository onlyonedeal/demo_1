package com.lily.mhl.DAO;

import com.lily.mhl.Utils.JDBCUtilByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Lily
 * @version 1.0
 * 开发通用BasicDao，该类是其他DAO的父类
 */
public class BasicDAO<T> {
    private QueryRunner qr = new QueryRunner();

    //开发通用的增删改操作，针对任意表
    public int update(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilByDruid.getConnection();
            return qr.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilByDruid.close(null, null, connection);
        }
    }

    //查询并返回多个对象

    /**
     * @param sql    sql语句，可已有？占位符
     * @param clazz  传入一个类的class对象，比如String.class，Integer.class，actor
     * @param params 传入参数的值，可以是多个
     * @return 返回List集合
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilByDruid.close(null, null, connection);
        }
    }

    //查询单个对象
    public T querySingle(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilByDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilByDruid.close(null, null, connection);
        }
    }

    //查询单个值
    public Object queryScalar(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilByDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilByDruid.close(null, null, connection);
        }
    }
}
