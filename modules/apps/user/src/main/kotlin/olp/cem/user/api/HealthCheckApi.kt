package olp.cem.user.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/health")
interface HealthCheckApi {

    @GetMapping
    fun healthCheck(): String
}