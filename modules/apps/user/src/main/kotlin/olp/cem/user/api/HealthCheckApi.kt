package olp.cem.user.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Tag(name = "Health Check", description = "Health check endpoints")
@RequestMapping("/api/v1/health")
interface HealthCheckApi {

    @Operation(
        summary = "Health check endpoint",
        description = "Returns the health status of the application"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Application is healthy"),
            ApiResponse(responseCode = "503", description = "Application is unhealthy")
        ]
    )
    @GetMapping
    fun healthCheck(): ResponseEntity<HealthCheckResponse>

    data class HealthCheckResponse(
        val status: String,
        val timestamp: String,
        val version: String,
        val uptime: String,
        val checks: Map<String, CheckStatus>
    )

    data class CheckStatus(
        val status: String,
        val message: String? = null
    )
}