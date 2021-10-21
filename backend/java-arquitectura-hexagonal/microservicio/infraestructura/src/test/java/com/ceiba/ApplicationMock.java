package com.ceiba;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.ceiba.agenda.adaptador.dao.DaoAgendaJpa;
import com.ceiba.agenda.adaptador.repositorio.RepositorioAgendaJpa;
import com.ceiba.agenda.puerto.dao.DaoAgenda;
import com.ceiba.agenda.puerto.repositorio.RepositorioAgenda;
import com.ceiba.agenda.servicios.ServicioCrearAgenda;
import com.ceiba.jpa.model.AgendaJpa;
import com.ceiba.jpa.model.MascotaJpa;
import com.ceiba.jpa.model.TipoMascotaJpa;
import com.ceiba.jpa.model.UsuarioJpa;
import com.ceiba.jpa.repository.ImpAgendaJpaRepository;
import com.ceiba.jpa.repository.ImpMascotaJpaRepository;
import com.ceiba.jpa.repository.ImpTipoMascotaJpaRepository;
import com.ceiba.jpa.repository.ImpUsuarioJpaRepository;
import com.ceiba.mascota.adaptador.dao.DaoMascotaJpa;
import com.ceiba.mascota.adaptador.repositorio.RepositorioMascotaJpa;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.tipomascota.adaptador.dao.DaoTipoMascotaJpa;
import com.ceiba.tipomascota.puerto.dao.DaoTipoMascota;
import com.ceiba.usuario.adaptador.dao.DaoUsuarioJpa;
import com.ceiba.usuario.adaptador.repositorio.RepositorioUsuarioJPA;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.ceiba")
//@Configuration
@EntityScan(basePackageClasses = {UsuarioJpa.class, TipoMascotaJpa.class, MascotaJpa.class, AgendaJpa.class})
@EnableJpaRepositories(basePackageClasses = {ImpUsuarioJpaRepository.class,
ImpTipoMascotaJpaRepository.class,ImpMascotaJpaRepository.class, ImpAgendaJpaRepository.class})
@EnableTransactionManagement(proxyTargetClass = true)
@TestPropertySource(properties = { "spring.config.location=classpath:application.yaml" })
public class ApplicationMock {


    @Bean
    public DataSource dataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setDataSource(dataSource2());
        sessionFactory.setPackagesToScan("com.ceiba");

        return sessionFactory;
    }
    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                sessionFactory().getObject());
        return transactionManager;
    }


    private final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.ddl-auto", "none");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }

    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) throws IOException {

        return Flyway.configure().locations("filesystem:../src/main/resources","filesystem:src/test/resources").baselineOnMigrate(true)
                .dataSource(dataSource).load();

    }
    /*
    @Bean
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }*/
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Primary
    @Bean
    public RepositorioMascota repositorioMascota(ImpMascotaJpaRepository impMascotaJpaRepository){
        return new RepositorioMascotaJpa(impMascotaJpaRepository);
    }

    @Primary
    @Bean
    public RepositorioUsuario repositorioUsuario(ImpUsuarioJpaRepository impUsuarioJpaRepository){
        return new RepositorioUsuarioJPA(impUsuarioJpaRepository);
    }

    @Primary
    @Bean
    public DaoMascota daoMascota(ImpMascotaJpaRepository impMascotaJpaRepository){
        return new DaoMascotaJpa(impMascotaJpaRepository);
    }

    @Primary
    @Bean
    public DaoTipoMascota daoTipoMascota(ImpTipoMascotaJpaRepository impTipoMascotaJpaRepository){
        return new DaoTipoMascotaJpa(impTipoMascotaJpaRepository);
    }

    @Primary
    @Bean
    public DaoUsuario daoUsuario(ImpUsuarioJpaRepository impUsuarioJpaRepository){
        return new DaoUsuarioJpa(impUsuarioJpaRepository);
    }

    @Primary
    @Bean
    public DaoAgenda daoAgenda(ImpAgendaJpaRepository impAgendaJpaRepository){
        return new DaoAgendaJpa(impAgendaJpaRepository);
    }

    @Primary
    @Bean
    public RepositorioAgenda repositorioAgenda(ImpAgendaJpaRepository impAgendaJpaRepository){
        return new RepositorioAgendaJpa(impAgendaJpaRepository);
    }
    /*
    @Primary
    @Bean
    public ManejadorCrearAgenda manejadorCrearAgenda(ServicioCrearAgenda servicioCrearAgenda, FabricaAgenda fabricaAgenda){
        return new ManejadorCrearAgenda(servicioCrearAgenda,fabricaAgenda);
    }*/}
