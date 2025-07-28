/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 */
@SpringBootApplication
@ImportRuntimeHints(PetClinicRuntimeHints.class)
public class PetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}
	

    /**
     * Calls the SimpleGreeting class to print a greeting message.
     */
    public static void callSimpleGreeting(String message) {
        org.springframework.samples.petclinic.service.SimpleGreeting greeting =
            new org.springframework.samples.petclinic.service.SimpleGreeting(message);
        greeting.greet();
    }
 
    /**
     * Prints the current active Spring profiles.
     */
    public static void printActiveProfiles() {
        String[] profiles = org.springframework.context.annotation.AnnotationConfigApplicationContext
            .getEnvironmentStatic().getActiveProfiles();
        if (profiles.length == 0) {
            System.out.println("No active Spring profiles.");
        } else {
            System.out.println("Active Spring profiles: " + String.join(", ", profiles));
        }
    }


}
