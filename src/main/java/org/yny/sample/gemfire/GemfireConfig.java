package org.yny.sample.gemfire;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.pdx.PdxSerializer;
import com.gemstone.gemfire.pdx.ReflectionBasedAutoSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.ConnectionEndpoint;
import org.yny.sample.gemfire.repositories.BookRepository;

@Configuration
@ClientCacheApplication(name = "SpringDataGemfireClientApp")
@EnablePdx(serializerBeanName = "pdxSerializer") //NB name of the PDX serializer bean
@EnableGemfireRepositories(basePackageClasses = BookRepository.class)
public class GemfireConfig {

    @Value("${org.yny.sample.gemfire.locator.host:localhost}")
    private String locatorHost;

    @Value("${org.yny.sample.gemfire.locator.port:10334}")
    private Integer locatorPort;

    @Value("${org.yny.sample.gemfire.dataModelPackage:org.yny.sample.model.*}")
    private String dataModelPackage;

    @Bean
    public PdxSerializer pdxSerializer() {
        return new ReflectionBasedAutoSerializer();
    }

    @Bean
    public ClientCacheFactoryBean gemfireCache() {
        ClientCacheFactoryBean clientCacheFactoryBean = new ClientCacheFactoryBean();
        //NB locator has to be configured on the **client** cache factory bean
        clientCacheFactoryBean.addLocators(new ConnectionEndpoint(locatorHost, locatorPort));
        //NB PDX serializer has to be configured with the data model package
        clientCacheFactoryBean.setPdxSerializer(new ReflectionBasedAutoSerializer(dataModelPackage));
        clientCacheFactoryBean.setClose(true);
        return clientCacheFactoryBean;
    }

    @Bean("booksRegion")
    public ClientRegionFactoryBean<Object, Object> booksRegion(GemFireCache gemfireCache) {
        ClientRegionFactoryBean<Object, Object> regionFactoryBean = new ClientRegionFactoryBean<>();
        regionFactoryBean.setCache(gemfireCache);
        regionFactoryBean.setClose(false);
        regionFactoryBean.setShortcut(ClientRegionShortcut.PROXY); //NB running as a client, no local caching
        return regionFactoryBean;
    }

}
