package own.little.project.olp

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import own.little.project.olp.config.sql.DataSourceConfiguration
import own.little.project.olp.config.sql.MySqlProperties

@OpenAPIDefinition
@SpringBootApplication
class OlpApplication

fun main(args: Array<String>) {
    runApplication<OlpApplication>(*args)
}
