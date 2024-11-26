package pt.ul.fc.css.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.ul.fc.css.example.demo.repositories.AuthorRepository;

@SpringBootTest
class DemoApplicationTests {

  @Autowired private AuthorRepository repository;
}
