package com.bat.generate;


import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成代码
 */
public class GenerateCode {
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateCode.class);

	public static void main(String[] args) throws Exception {
		generate("D:\\workspace\\WeCloud\\mybatis-generator\\src\\test\\java\\com\\bat\\generate\\local-database-generator.xml");
	}

	/**
	 * @param f
	 * @throws Exception
	 */
	public static void generate(String f) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(f);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(new NullProgressCallback());
		
		warnings.forEach(logger::warn);
		logger.warn(warnings.size() + "");
	}

}
