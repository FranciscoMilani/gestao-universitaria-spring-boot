package br.ucs.ffmilani.GestaoUni;

import br.ucs.ffmilani.GestaoUni.config.DataInsertionConfiguration;
import br.ucs.ffmilani.GestaoUni.controller.swing.MainMenuController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import javax.swing.*;

@SpringBootApplication
@AllArgsConstructor
public class GestaoUniApplication {

	public DataSource dataSource;

	public static void main(String[] args) {
		new SpringApplicationBuilder(GestaoUniApplication.class)
				.headless(false)
				.run(args);
	}

	@Bean
	@ConditionalOnProperty(prefix = "dados", value = "cadastro")
	ApplicationRunner applicationRunner(@Autowired DataInsertionConfiguration insertConfig) {
		return args -> {
			insertConfig.carregaDados();
		};
	}

	@Bean(name = "dbms")
	public String dbms(){
		try {
			return dataSource.getConnection().getMetaData().getDatabaseProductName();
		} catch (Exception e) {
			return "?";
		}
	}

	// Invoca o Swing
	@Bean
	CommandLineRunner runner(@Autowired MainMenuController controller) {
		return args -> SwingUtilities.invokeLater(() -> controller.prepareAndOpen());
	}
}