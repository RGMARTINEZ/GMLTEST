package com.example.library.ejb;

import com.example.library.entity.Book;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class BookService {
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager em;

    public Book addBook(Book book) {
        em.persist(book);
        return book;
    }

    public List<Book> getBooks() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public Book getBook(Long id) {
        return em.find(Book.class, id);
    }

    public Book updateBook(Book book) {
        return em.merge(book);
    }

    public void deleteBook(Long id) {
        Book b = em.find(Book.class, id);
        if (b != null) em.remove(b);
    }
}
