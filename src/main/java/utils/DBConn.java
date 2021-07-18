package utils;

import org.apache.catalina.User;
import services.v1.user.model.UserModel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConn {

    private String database = "dadn1cddkhm83j";
    private String query = null;
    private Class model = null;

    public DBConn(String database, String query, Class model){
        this.database =database;
        this.query = query;
        this.model = model;
    }

    public <T> List<T> execute() throws Exception {
        if(this.query.equals("")) {
            throw new Exception("Query está em branco");
        }
        List<T> resultList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://ec2-3-212-75-25.compute-1.amazonaws.com:5432/"+this.database+"?sslmode=require",
                    "plqutghdamllgm", "91c33fd5b27a61a5d01e872f66813058026eb512289d391605cb387057855093");
            PreparedStatement pstm = connection.prepareStatement(this.query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                T item = (T) this.model.getConstructor().newInstance();
                for (Field fieldModel : this.model.getDeclaredFields()) {
                    fieldModel.setAccessible(true);
                    Object value = this.toValueType(fieldModel, rs);
                    this.setItem(item, fieldModel, value);
                }
                resultList.add(item);
            }
            rs.close();
            pstm.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private Object toValueType(Field field, ResultSet rs) throws SQLException {
        if (field.getType() == Long.class) {
            return rs.getLong(field.getName());
        } else if (field.getType() == BigDecimal.class) {
            return rs.getBigDecimal(field.getName());
        } else if (field.getType() == Integer.class) {
            return rs.getInt(field.getName());
        } else if (field.getType() == Float.class) {
            return rs.getFloat(field.getName());
        } else if (field.getType() == Byte.class) {
            return rs.getByte(field.getName());
        } else if (field.getType() == Boolean.class) {
            return rs.getBoolean(field.getName());
        } else {
            return rs.getString(field.getName());
        }
    }

    private <T> T setItem(T item, Field field, Object value) {
        try {
            if (field.getType() == Long.class || field.getType() == long.class) {
                field.set(item, field.getType().getConstructor(new Class[]{long.class}).newInstance(value));
            } else if (field.getType() == Integer.class || field.getType() == int.class) {
                field.set(item, field.getType().getConstructor(new Class[]{int.class}).newInstance(value));
            } else {
                field.set(item, field.getType().getConstructor(field.getType()).newInstance(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

}
