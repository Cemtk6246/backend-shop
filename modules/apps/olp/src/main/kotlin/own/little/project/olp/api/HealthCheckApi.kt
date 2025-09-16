package own.little.project.olp.api

import io.grpc.health.v1.HealthCheckResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import own.little.project.olp.model.HealthCheckResponseDto

@RequestMapping("/health")
interface HealthCheckApi {

    @GetMapping(value = ["/check"], produces = [APPLICATION_JSON_VALUE])
    fun checkApplicationHealth() : HealthCheckResponseDto;
}