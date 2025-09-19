package olp.cem.user.api.controller

import olp.cem.user.api.HealthCheckApi
import org.springframework.boot.info.BuildProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.lang.management.ManagementFactory
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.sql.DataSource

@RestController
class HealthCheckController(
    private val dataSource: DataSource,
    private val buildProperties: BuildProperties?
) : HealthCheckApi {

    override fun healthCheck(): ResponseEntity<HealthCheckApi.HealthCheckResponse> {
        val checks = mutableMapOf<String, HealthCheckApi.CheckStatus>()
        var overallHealthy = true

        // Database check
        try {
            dataSource.connection.use { connection ->
                val isValid = connection.isValid(5) // 5 seconds timeout
                checks["database"] = if (isValid) {
                    HealthCheckApi.CheckStatus("UP", "Database connection is healthy")
                } else {
                    overallHealthy = false
                    HealthCheckApi.CheckStatus("DOWN", "Database connection is not valid")
                }
            }
        } catch (e: Exception) {
            overallHealthy = false
            checks["database"] = HealthCheckApi.CheckStatus("DOWN", "Database connection failed: ${e.message}")
        }

        // Memory check
        val runtime = Runtime.getRuntime()
        val maxMemory = runtime.maxMemory()
        val totalMemory = runtime.totalMemory()
        val freeMemory = runtime.freeMemory()
        val usedMemory = totalMemory - freeMemory
        val memoryUsagePercent = (usedMemory.toDouble() / maxMemory.toDouble()) * 100

        checks["memory"] = if (memoryUsagePercent < 90) {
            HealthCheckApi.CheckStatus("UP", "Memory usage: ${String.format("%.2f", memoryUsagePercent)}%")
        } else {
            overallHealthy = false
            HealthCheckApi.CheckStatus("DOWN", "High memory usage: ${String.format("%.2f", memoryUsagePercent)}%")
        }

        // Disk space check (simplified)
        checks["diskSpace"] = HealthCheckApi.CheckStatus("UP", "Disk space check not implemented")

        val response = HealthCheckApi.HealthCheckResponse(
            status = if (overallHealthy) "UP" else "DOWN",
            timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
            version = buildProperties?.version ?: "unknown",
            uptime = getUptime(),
            checks = checks
        )

        return if (overallHealthy) {
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response)
        }
    }

    private fun getUptime(): String {
        val runtimeMXBean = ManagementFactory.getRuntimeMXBean()
        val uptimeMillis = runtimeMXBean.uptime
        val uptimeSeconds = uptimeMillis / 1000
        val hours = uptimeSeconds / 3600
        val minutes = (uptimeSeconds % 3600) / 60
        val seconds = uptimeSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}