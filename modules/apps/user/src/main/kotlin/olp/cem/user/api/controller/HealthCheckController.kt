package olp.cem.user.api.controller

import olp.cem.user.api.HealthCheckApi
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController : HealthCheckApi {

    override fun healthCheck(): String {
        return "OK"
    }
}