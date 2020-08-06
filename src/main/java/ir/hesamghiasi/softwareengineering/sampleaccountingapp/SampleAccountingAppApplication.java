package ir.hesamghiasi.softwareengineering.sampleaccountingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ir.hesamghiasi.softwareengineering.sampleaccountingapp"})
@EnableAutoConfiguration
public class SampleAccountingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleAccountingAppApplication.class, args);
	}

}
