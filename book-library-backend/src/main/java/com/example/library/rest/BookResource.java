package com.example.library.rest;

import com.example.library.ejb.BookService;
import com.example.library.entity.Book;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    @Inject
    private BookService bookService;

    @GET
    public List<Book> listBooks() {
        return bookService.getBooks();
    }

    @GET @Path("/{id}")
    public Response getBook(@PathParam("id") Long id) {
        Book b = bookService.getBook(id);
        if (b == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found").build();
        return Response.ok(b).build();
    }

    @POST
    public Response addBook(Book book, @Context UriInfo uriInfo) {
        if (book.getTitle() == null || book.getAuthor() == null ||
                book.getPublicationYear() == null || book.getIsbn() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("All fields required.").build();
        }
        Book saved = bookService.addBook(book);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(saved.getId().toString());
        return Response.created(builder.build()).entity(saved).build();
    }

    @PUT @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, Book book) {
        Book existing = bookService.getBook(id);
        if (existing == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found").build();
        book.setId(id);
        return Response.ok(bookService.updateBook(book)).build();
    }

    @DELETE @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        Book existing = bookService.getBook(id);
        if (existing == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found").build();
        bookService.deleteBook(id);
        return Response.noContent().build();
    }
}
