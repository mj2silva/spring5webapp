package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher manuelPub = new Publisher("Manuel", "Cavero y muñoz 220", "La Libertad", "Trujillo", "13001");
        Author manuel = new Author("Manuel", "Silva");
        Book ddd = new Book("Domain Driven Design","1sa1151546166");

        publisherRepository.save(manuelPub);

        manuel.getBooks().add(ddd);
        ddd.getAuthors().add(manuel);
        manuelPub.getBooks().add(ddd);
        ddd.setPublisher(manuelPub);

        authorRepository.save(manuel);
        bookRepository.save(ddd);
        publisherRepository.save(manuelPub);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "365959544844");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        manuelPub.getBooks().add(noEJB);
        noEJB.setPublisher(manuelPub);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(manuelPub);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + manuelPub.getBooks().size());
    }
}
