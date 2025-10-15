package co.com.activos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Usa allowedOriginPatterns para soportar patrones y evitar problemas con credenciales
                .allowedOriginPatterns(
                        "http://localhost:4200",
                        "https://oficina-virtual-194964492367.us-east1.run.app",
                        "https://front-gestor-document-194964492367.us-east1.run.app",
                        "https://reforma-lab-2-194964492367.us-east1.run.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Content-Disposition")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
