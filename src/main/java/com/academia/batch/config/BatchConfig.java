package com.academia.batch.config;

import com.academia.batch.model.AsignacionReporte;
import com.academia.batch.model.Clasificacion;
import com.academia.batch.processor.AsignacionProcessor;
import com.academia.batch.processor.ClasificacionProcesor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {


    @Bean
    public Job jujutsuJobv3(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("jujutsuJob", jobRepository)
                .start(step1)
                .next(step2)
                .build();
    }


    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, DataSource dataSource) {
        return new StepBuilder("step1-clasificacion", jobRepository)
                .<Clasificacion, Clasificacion>chunk(10, transactionManager)
                .reader(csvReader())
                .processor(new ClasificacionProcesor())
                .writer(sqlWriter(dataSource))
                .build();
    }


    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager, DataSource dataSource, MongoTemplate mongoTemplate) {
        return new StepBuilder("step2-asignacion", jobRepository)
                .<Clasificacion, AsignacionReporte>chunk(5, transactionManager)
                .reader(sqlReader(dataSource))
                .processor(new AsignacionProcessor())
                .writer(mongoWriter(mongoTemplate))
                .build();
    }



    @Bean
    public FlatFileItemReader<Clasificacion> csvReader() {
        return new FlatFileItemReaderBuilder<Clasificacion>()
                .name("csvReader")
                .resource(new ClassPathResource("avistamientos.csv"))
                // Ignoramos la primera línea si tiene cabeceras
                .linesToSkip(1)
                .delimited()
                .names("idReporte", "ubicacion", "densidadEnergia", "numVictimas")
                .targetType(Clasificacion.class)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Clasificacion> sqlWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Clasificacion>()
                .dataSource(dataSource)
                .sql("INSERT INTO clasificacion (idReporte, ubicacion, densidadEnergia, numVictimas, grado) " +
                        "VALUES (:idReporte, :ubicacion, :densidadEnergia, :numVictimas, :grado)")
                .beanMapped()
                .build();
    }



    @Bean
    public JdbcCursorItemReader<Clasificacion> sqlReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Clasificacion>()
                .name("sqlReader")
                .dataSource(dataSource)
                .sql("SELECT idReporte as idReporte, ubicacion, densidadEnergia as densidadEnergia, numVictimas as numVictimas, grado FROM clasificacion")
                .rowMapper(new BeanPropertyRowMapper<>(Clasificacion.class))
                .build();
    }

    @Bean
    public MongoItemWriter<AsignacionReporte> mongoWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<AsignacionReporte>()
                .template(mongoTemplate)
                .collection("misiones_finales")
                .build();
    }
}