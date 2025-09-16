package own.little.project.olp.api.controller

import org.springframework.web.bind.annotation.RestController
import own.little.project.olp.api.HealthCheckApi
import own.little.project.olp.model.HealthCheckResponseDto

@RestController
class HealthCheckController : HealthCheckApi {

    override fun checkApplicationHealth(): HealthCheckResponseDto {
        return HealthCheckResponseDto("UP");
    }
}