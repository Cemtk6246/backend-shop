package olp.cem.user.config.sql

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource.mysql")
open class MySqlProperties(
    var url: String,
    var username: String,
    var password: String,
    var driverClassName: String
)