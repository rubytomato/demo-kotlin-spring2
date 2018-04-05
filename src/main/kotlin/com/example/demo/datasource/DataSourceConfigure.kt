package com.example.demo.datasource

/**
 * this code is investigation.
 */
/*
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages=["com.example.demo.repository"],
    entityManagerFactoryRef="entityManagerFactory",
    transactionManagerRef="transactionManager"
)
class DataSourceConfigure {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    fun datasource(): DataSource =
            DataSourceBuilder.create().build()

    @Bean("entityManagerFactory")
    fun entityManagerFactory(_builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val props = mapOf("hibernate.show_sql" to "true",
                "hibernate.format_sql" to "true",
                "hibernate.use_sql_comments" to "true",
                "hibernate.generate_statistics" to "true")
        return _builder.dataSource(datasource())
                .persistenceUnit("default-PU")
                .packages("com.example.demo.entity")
                .properties(props)
                .build()
    }

    @Bean("transactionManager")
    fun transactionManager(_entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            entityManagerFactory = _entityManagerFactory
        }
    }

    @Bean
    fun customizer() = HibernatePropertiesCustomizer { props ->
        props["hibernate.show_sql"] = true
        props["hibernate.format_sql"] = true
        props["hibernate.use_sql_comments"] = true
        props["hibernate.generate_statistics"] = false
    }

}
*/