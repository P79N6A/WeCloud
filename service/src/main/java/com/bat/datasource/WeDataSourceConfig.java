package com.bat.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-04 15:17
 **/
@Configuration
@MapperScan(basePackages = "com.bat.dao.we",sqlSessionTemplateRef = "weSqlSessionTemplate")
public class WeDataSourceConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.we.datasource")
	public DataSource weDataSource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	@Bean
	public SqlSessionFactory weSqlSessionFactory(@Qualifier("weDataSource")DataSource dataSource)throws Exception{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		bean.setConfiguration(configuration);
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath://resources/mapper/we/*.xml"));
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/bat//we/*.xml"));
		return bean.getObject();
	}

	@Bean
	public DataSourceTransactionManager weDataSourceTransactionManager(@Qualifier("weDataSource")DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionTemplate weSqlSessionTemplate(@Qualifier("weSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
