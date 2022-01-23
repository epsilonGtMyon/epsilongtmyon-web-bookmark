package epsilongtmyon.app.common.config;

import java.io.IOException;
import java.time.Duration;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Spring MVCの設定
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	//org.springframework.boot.autoconfigure.web.ResourceProperties
	//がとれない

	private final WebProperties webProperties;

	//2.6でResouces はinjectionされなくなった模様
	//https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.6-Release-Notes#web-resources-configuration
	public WebConfig(WebProperties webProperties) {
		this.webProperties = webProperties;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		final Resources resourceProperties = webProperties.getResources();
		registry.addResourceHandler("/**")
				.addResourceLocations(resourceProperties.getStaticLocations())
				.setCachePeriod(getSeconds(resourceProperties.getCache().getPeriod()))
				.setCacheControl(resourceProperties.getCache().getCachecontrol().toHttpCacheControl())
				.resourceChain(true)
				.addResolver(new SpaPathResourceResolver());
	}

	private Integer getSeconds(Duration cachePeriod) {
		return (cachePeriod != null) ? (int) cachePeriod.getSeconds() : null;
	}

	static class SpaPathResourceResolver extends PathResourceResolver {

		protected Resource getResource(String resourcePath, Resource location) throws IOException {
			Resource resource = super.getResource(resourcePath, location);
			if (resource != null) {
				return resource;
			}
			if (resourcePath.startsWith("api")) {
				//apiは対象外
				return null;
			}

			return super.getResource("index.html", location);
		}
	}

}
