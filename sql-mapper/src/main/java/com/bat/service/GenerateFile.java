package com.bat.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bat.domain.Field;
import com.bat.type.DbColumnType;
import com.bat.type.MySqlTypeConvert;
import com.bat.util.DBUtil;
import com.bat.util.FieldNameUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: caoke
 * @Date: 2019/3/5 10:43
 * @Version 1.0
 */
public class GenerateFile {

    public static void out(String pkg,String tableName,String className,String path)throws Exception {

        List<Field> fields = getFields(tableName);


        StringBuilder sb = getStringBuilder(pkg, className, fields);
        String filepath = path+pkg.replace(".","/")+"/"+className+".java";
        System.out.println("filepath:"+filepath);
        File file = new File(filepath);
        if(!file.getParentFile().exists()){
            file.mkdirs();
        }
        file.canWrite();
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        out.write(sb.toString().getBytes());

    }

    private static StringBuilder getStringBuilder(String pkg, String className, List<Field> fields) {
        StringBuilder sb = new StringBuilder();
        StringBuilder importStr = new StringBuilder();
        importStr.append("package "+pkg+";");
        importStr.append("\n");
        importStr.append("\n");
        importStr.append("import java.io.Serializable;");
        importStr.append("\n");
        importStr.append("import lombok.Getter;");
        importStr.append("\n");
        importStr.append("import lombok.Setter;");
        importStr.append("\n");
        importStr.append("\n");
        sb.append("@Getter");
        sb.append("\n");
        sb.append("@Setter");
        sb.append("\n");
        sb.append("public class "+className+" implements Serializable {");
        sb.append("\n");
        sb.append("\n");
        for(Field field:fields){
            sb.append("\t");
            sb.append("private "+field.getType()+" "+field.getName()+";");
            sb.append("\n");
            sb.append("\n");
            if(DbColumnType.getPackage(field.getType()) != null){
                importStr.append("import " + DbColumnType.getPackage(field.getType())).append(";");
                importStr.append("\n");
            }
        }
        sb.append("}");
        importStr.append("\n").append(sb);
        return importStr;
    }

    private static List<Field> getFields(String tableName) throws SQLException {
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from "+tableName+" where 1=2");
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        DBUtil.close(rs,statement,connection);

        JSONObject metaJson = JSONObject.parseObject(JSONObject.toJSONString(resultSetMetaData));
        JSONArray arr = metaJson.getJSONArray("fields");
        List<Field> fields = new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            JSONObject obj = arr.getJSONObject(i);
            Field field = new Field();
            field.setName(FieldNameUtil.getName(obj.getString("originalName")));
            field.setType(MySqlTypeConvert.processTypeConvert(obj.getString("mysqlType")).getType());
            fields.add(field);
        }
        return fields;
    }
}
