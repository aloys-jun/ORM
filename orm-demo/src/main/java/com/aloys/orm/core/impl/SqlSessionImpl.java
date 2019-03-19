package com.aloys.orm.core.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.aloys.orm.annotation.Column;
import com.aloys.orm.annotation.Table;
import com.aloys.orm.core.SqlSession;
import com.aloys.orm.datasource.DataSource;
import com.aloys.orm.datasource.impl.DataSourceImpl;
import com.aloys.orm.exception.ORMException;


public class SqlSessionImpl implements SqlSession {

	private DataSource dataSource;
	
	public SqlSessionImpl(){
		this.dataSource = new DataSourceImpl();
	}

	@Override
	public <T> int insert(T entity) {
		int row = 0;
		if(entity == null){
			throw new ORMException("实体对象为空");
		}
		
		//构建SQL语句
		StringBuilder sql = new StringBuilder();
		//参数列表
		List<Object> parameters = new ArrayList<>();
		
		builderSqlByEntity(entity,sql,parameters);
		
		try(Connection connect = dataSource.getDataSource();
				PreparedStatement statement = connect.prepareStatement(sql.toString())){
			if(parameters.size() > 0){
				for(int i = 0; i < parameters.size(); i++){
					statement.setObject(i+1, parameters.get(i));
				}
			}
			//执行SQL
			row = statement.executeUpdate();
		}catch(Exception e){
			throw new ORMException("执行SQL语句出错:" + e.getMessage());
		}
		
		
		return row;
	}
	
	
	
	@SuppressWarnings("unused")
	private<T> void builderSqlByEntity(T entity,StringBuilder sql,List<Object> parameters){
		//根据实体对象生成SQL语句
		Class<?> clazz = entity.getClass();
		
		//如果table没有指定名字，则用类名代替
		String tableName = clazz.getSimpleName();
		if(clazz.isAnnotationPresent(Table.class)){
			Table table = clazz.getAnnotation(Table.class);
			if(!"".equals(table.value())){
				tableName = table.value();
			}
		}
		
		sql.append("insert into ").append(tableName).append("(");
		
		Field[] fields = clazz.getDeclaredFields();
		try{
			for(Field field : fields){
				String columnName = field.getName();
				if(field.isAnnotationPresent(Column.class)){
					Column column = field.getAnnotation(Column.class);
					if(!"".equals(column.value())){
						columnName = column.value();
					}
				}
				
				//属性描述器
				PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
				Method method = descriptor.getReadMethod();
				Object object = method.invoke(entity);
				
				if(object != null){
					sql.append(columnName + ",");
					parameters.add(object);
				}
			}
			
			sql.deleteCharAt(sql.length()-1).append(") values (");
			for(Object obj : parameters){
				sql.append("?,");
			}
			sql.deleteCharAt(sql.length()-1).append(")");
			System.out.println(sql);
			
		}catch(Exception e){
			throw new ORMException("执行SQL语句出错:" + e.getMessage());
		}
		
	}

}
