package hu.pbes.blog.junitextensions;

import org.springframework.boot.SpringApplication;

public class TestJunitExtensionsApplication {

	public static void main(String[] args) {
		SpringApplication.from(JunitExtensionsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
