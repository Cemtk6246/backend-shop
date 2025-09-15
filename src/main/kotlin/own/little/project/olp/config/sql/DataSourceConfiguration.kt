package own.little.project.olp.config.sql

import javax.sql.DataSource
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DriverManagerDataSource
import own.little.project.olp.config.sql.MySqlProperties

@Configuration
@EnableConfigurationProperties(MySqlProperties::class)
class DataSourceConfiguration(
    private val mySqlProperties: MySqlProperties,
) {

    @Bean
    @Primary
    fun mysqlDataSource() : DataSource {
        val dataSource = DriverManagerDataSource();
        dataSource.setUrl(mySqlProperties.url);
        dataSource.setUsername(mySqlProperties.username);
        dataSource.setPassword(mySqlProperties.password);
        dataSource.setDriverClassName(mySqlProperties.driverClassName);

        return dataSource;
    }
}