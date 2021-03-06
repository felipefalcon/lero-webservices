package utils;

import utils.model.DBConfModel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

    private final HashMap<String, DBConfModel> databases = new HashMap<String, DBConfModel>();
    private DBConfModel DBSelected = null;
    private String DBName = null;
    private String query = null;
    private HashMap<Integer, Object> paramsQuery = new HashMap<>();
    private Class model = null;

    public DBUtil(String DBName, String query, Class model){
        this.query = query;
        this.model = model;
        this.fillDatabasesConfs();
        this.DBSelected = this.databases.get(DBName);
        this.DBName = DBName;
    }

    public DBUtil(String DBName, String query, HashMap<Integer, Object> paramsQuery, Class model){
        this.query = query;
        this.model = model;
        this.paramsQuery = paramsQuery;
        this.fillDatabasesConfs();
        this.DBSelected = this.databases.get(DBName);
        this.DBName = DBName;
    }

    private void fillDatabasesConfs(){
        this.databases.put("DB1", new DBConfModel(
                "ec2-3-212-75-25.compute-1.amazonaws.com",
                5432,
                "dadn1cddkhm83j",
                "plqutghdamllgm",
                "91c33fd5b27a61a5d01e872f66813058026eb512289d391605cb387057855093"
        ));
    }

    public <T> List<T> execute() throws Exception {
        if (this.query.equals("")) {
            throw new Exception("Query está em branco");
        }
        if (this.DBSelected == null) {
            throw new Exception("Configuração da base informada não encontrada (Base: "+this.DBName+" )");
        }
        List<T> resultList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://"+this.DBSelected.host+":"+this.DBSelected.port+"/"+this.DBSelected.database+"?sslmode=require",
                    this.DBSelected.user, this.DBSelected.password);
            PreparedStatement pstm = this.createPreparedStatement(connection);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                T item = (T) this.model.getConstructor().newInstance();
                ArrayList<String> propsToFill = this.getColumnsQuery(rs);
                for (Field fieldModel : this.model.getDeclaredFields()) {
                    if (!propsToFill.contains(fieldModel.getName())) continue;
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

    private ArrayList<String> getColumnsQuery(ResultSet rs) throws SQLException {
        ArrayList<String> columnsQuery = new ArrayList<String>();
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            columnsQuery.add(rsmd.getColumnName(i));
        }
        return columnsQuery;
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
            } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                field.set(item, field.getType().getConstructor(new Class[]{boolean.class}).newInstance(value));
            } else {
                field.set(item, field.getType().getConstructor(field.getType()).newInstance(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    private PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement(this.query);
        if (!paramsQuery.isEmpty()) {
            paramsQuery.forEach((key, value) -> {
                try {
                    if(value instanceof Integer){
                        pstm.setInt(key, (Integer) value);
                    } else if (value instanceof Float) {
                        pstm.setFloat(key, (Float) value);
                    } else if (value instanceof Long) {
                        pstm.setLong(key, (Long) value);
                    } else if (value instanceof BigDecimal) {
                        pstm.setBigDecimal(key, (BigDecimal) value);
                    } else if (value instanceof Boolean) {
                        pstm.setBoolean(key, (Boolean) value);
                    } else {
                        pstm.setString(key, (String) value);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        }
        return pstm;
    }

}
