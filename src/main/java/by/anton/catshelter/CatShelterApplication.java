package by.anton.catshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class CatShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatShelterApplication.class, args);
    }

    @Bean
    public DataSource getDataSource() {
        Properties properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/database.properties");
            properties.load(fis);
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.url(properties.getProperty("url"));
            dataSourceBuilder.username(properties.getProperty("user"));
            dataSourceBuilder.password(properties.getProperty("passw"));
            return dataSourceBuilder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
