package br.com.teste.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;



@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration
{

    @Override
    protected String getKeyspaceName() {
        return "obk_callback";
    }


    @Bean
    public CassandraClusterFactoryBean cluster(){
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
            cluster.setContactPoints("127.0.0.1");
        cluster.setPort(9042);
        cluster.setJmxReportingEnabled(false);

        return cluster;
    }




}
