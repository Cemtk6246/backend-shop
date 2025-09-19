package olp.cem.flyway.config.db

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties(MySqlProperties::class)
class DataSourceConfiguration(
    private val mySqlProperties: MySqlProperties,
) {

    @Bean
    fun mysqlDataSource() : DataSource {
        val dataSource = DriverManagerDataSource();
        dataSource.url = mySqlProperties.url;
        dataSource.username = mySqlProperties.username;
        dataSource.password = mySqlProperties.password;
        dataSource.setDriverClassName(mySqlProperties.driverClassName);

        return dataSource;
    }
}