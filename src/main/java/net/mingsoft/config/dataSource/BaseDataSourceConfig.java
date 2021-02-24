package net.mingsoft.config.dataSource;

import java.util.Base64;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@AutoConfigureAfter(DruidConfig.class)
@MapperScan(basePackages = { "**.dao", "**.mapper" }, sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class BaseDataSourceConfig {
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;

	// 数据源1 由jdbc.properties自动注入属性值
	@Value("${spring.datasource.base.url:#{null}}")
	private String dbUrl;
	@Value("${spring.datasource.base.username: #{null}}")
	private String username;
	@Value("${spring.datasource.base.password:#{null}}")
	private String password;
	@Value("${spring.datasource.base.driverClassName:#{null}}")
	private String driverClassName;

	@Bean(name = "baseDataSource")
	//@ConfigurationProperties(prefix = "spring.datasource.base")
	@Primary
	public DataSource dataSource() {
		// DataSource dataSource = DruidDataSourceBuilder.create().build();
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(this.dbUrl);
		dataSource.setUsername(this.username);
		byte[] decoded = Base64.getDecoder().decode(this.password);
		String decodeStr = new String(decoded);

		dataSource.setPassword(decodeStr);
		dataSource.setDriverClassName(this.driverClassName);
		DruidConfig druidConfig = new DruidConfig();
		dataSource = druidConfig.dataSource(dataSource);
		return dataSource;
	}

	@Bean(name = "baseTransactionManager")
	@Primary
	public DataSourceTransactionManager setTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public DatabaseIdProvider databaseIdProvider() {
		DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
		Properties p = new Properties();
		p.setProperty("Oracle", "oracle");
		p.setProperty("MySQL", "mysql");
		p.setProperty("PostgreSQL", "postgresql");
		p.setProperty("DB2", "db2");
		p.setProperty("SQL Server", "sqlServer");
		databaseIdProvider.setProperties(p);
		return databaseIdProvider;
	}

	@Bean(name = "baseSqlSessionFactory")
	@Primary
	public SqlSessionFactory setSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setDatabaseIdProvider(databaseIdProvider());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return bean.getObject();
	}

	@Bean(name = "baseSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate setSqlSessionTemplate(
			@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}