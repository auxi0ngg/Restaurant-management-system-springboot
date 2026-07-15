package com.auxiongg.restaurant;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(ConfigurableApplicationContext context) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {

                System.out.println("\n==========================");
                System.out.println("Flyway bean exists: " + context.containsBean("flyway"));
                System.out.println("==========================");

                ConditionEvaluationReport report =
                        ConditionEvaluationReport.get(context.getBeanFactory());

                System.out.println("\n========== FLYWAY CONDITION REPORT ==========");

                report.getConditionAndOutcomesBySource().forEach((source, outcomes) -> {
                    if (source.contains("Flyway")) {
                        System.out.println("\n" + source);

                        outcomes.forEach(outcome -> {
                            System.out.println(" -> " + outcome.getOutcome());
                        });
                    }
                });

                System.out.println("\n========== END REPORT ==========\n");
            }
        };
    }
}