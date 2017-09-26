package com.puigros.configuration;
import com.couchbase.client.java.Bucket;
import com.puigros.couchbase.dto.secondaryBucket.Hotel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gpuigros on 22/08/17.
 */

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.cluster.bucket}")
    private String bucketName;

    //https://docs.spring.io/spring-data/couchbase/docs/current/reference/html/

    @Value("${couchbase.cluster.password}")
    private String password;

    @Value("${couchbase.cluster.ip}")
    private String ip;

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(this.ip);
    }

    @Override
    protected String getBucketName() {
        return this.bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return this.password;
    }

    @Bean
    public Bucket memBucket() throws Exception {
        return couchbaseCluster().openBucket("memcachedPoC", "memcachedPoC");
    }
    @Bean

    public CouchbaseTemplate menBasedTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(
                couchbaseClusterInfo(), memBucket(),
                mappingCouchbaseConverter(), translationService());
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }
    @Override
    public void configureRepositoryOperationsMapping(
            RepositoryOperationsMapping baseMapping) {
        try {
            baseMapping.mapEntity(Hotel.class, menBasedTemplate());
        } catch (Exception e) {
            //custom Exception handling
        }
    }
}