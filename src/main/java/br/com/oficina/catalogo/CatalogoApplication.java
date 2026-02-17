package br.com.oficina.catalogo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CatalogoApplication {

    private static final Logger logger = LoggerFactory.getLogger(CatalogoApplication.class);

	public static void main(String[] args) {
        logger.info("Aplicação Catalogo iniciada com sucesso.");
		SpringApplication.run(CatalogoApplication.class, args);
	}

}
