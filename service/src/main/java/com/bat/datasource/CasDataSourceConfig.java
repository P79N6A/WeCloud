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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @program: WeCloud
 * @description:
 * @author: ke.Cao
 * @create: 2018-12-04 15:17
 **/
@Configuration
@MapperScan(basePackages = "com.bat.dao.cas",sqlSessionTemplateRef = "casSqlSessionTemplate")
public class CasDataSourceConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.cas.datasource")
	public DataSource casDataSource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	@Bean
	public SqlSessionFactory casSqlSessionFactory(@Qualifier("casDataSource")DataSource dataSource)throws Exception{
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath://resources/mapper/cas/*.xml"));
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/bat//cas/*.xml"));
		return bean.getObject();
	}

	@Bean
	public DataSourceTransactionManager casDataSourceTransactionManager(@Qualifier("casDataSource")DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionTemplate casSqlSessionTemplate(@Qualifier("casSqlSessionFactory")SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
