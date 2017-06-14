package com.dasa;

import com.dasa.domain.DadoPopulacional;
import com.dasa.repository.DadosPopulacionaisRepository;
import com.dasa.utils.DatasetReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ComponentScan()
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    @Autowired
    DadosPopulacionaisRepository dadosPopulacionaisRepository;

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(Application.class);
    
    @PostConstruct
    public void init() {

        final Path path = Paths.get("src/main/resources/datasets", "dados_populacionais.csv");
        try {
            Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));

            DatasetReader datasetReader = new DatasetReader(reader);

            final List<DadoPopulacional> dadoPopulacionals = datasetReader.readDataset();

            dadosPopulacionaisRepository.deleteAll();

            for(DadoPopulacional d : dadoPopulacionals) {
                dadosPopulacionaisRepository.save(d);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao popular a Base de Dados", e);
        }
        

    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }


}