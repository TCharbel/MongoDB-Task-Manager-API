package in.karlfahd.springbootmongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Validator;

@Configuration
public class ValidationConfig {
	
	
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validationMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }
/*
	@Bean
	public ValidatingMongoEventListener validationMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}
	
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		return LocalValidatorFactoryBean();
	}
*/

/*	private LocalValidatorFactoryBean LocalValidatorFactoryBean() {
		// TODO Auto-generated method stub
		return null;
	}*/
}
