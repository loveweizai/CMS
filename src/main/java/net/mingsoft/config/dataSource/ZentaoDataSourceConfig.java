package net.mingsoft.config.dataSource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

import java.util.Base64;

@Configuration
@MapperScan(basePackages = {"**.sqlserver"}, sqlSessionTemplateRef = "zentaoSqlSessionTemplate")
public class ZentaoDataSourceConfig {
	
	// 数据源1 由jdbc.properties自动注入属性值
	@Value("${spring.datasource.zentao.url:#{null}}")
	private String dbUrl;
	@Value("${spring.datasource.zentao.username: #{null}}")
	private String username;
	@Value("${spring.datasource.zentao.password:#{null}}")
	private String password;
	@Value("${spring.datasource.zentao.driverClassName:#{null}}")
	private String driverClassName;
	
    @Bean(name = "zentaoDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.zentao")
    public DataSource setDataSource() {
    	DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(this.dbUrl);
		dataSource.setUsername(this.username);
        byte[] decoded = Base64.getDecoder().decode(this.password);
        String decodeStr = new String(decoded);
        dataSource.setPassword(decodeStr);
//		dataSource.setPassword(this.password);
		dataSource.setDriverClassName(this.driverClassName);
		DruidConfig druidConfig = new DruidConfig();
		dataSource = druidConfig.dataSource(dataSource);
		return dataSource;
        //return new DruidDataSource();
    }

    @Bean(name = "zentaoTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("zentaoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "zentaoSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("zentaoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/sqlserver/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "zentaoSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("zentaoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}