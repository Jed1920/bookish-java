package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;

import java.util.ArrayList;
import java.util.List;

public class BookModel {
    private Book book;

    public BookModel(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return book.getTitle();
    }

    public String getAuthor() {
        return book.getAuthor();
    }

    public Integer getId(){ return book.getId();}

    public String getAddCopyUrl() { return String.format("/library/%d/add-copy", getId());}

    public List<CopyModel> getCopies() {
        List<CopyModel> copies = new ArrayList<>();

        for (int copyId : book.getBookCopys()) {
            copies.add(new CopyModel(copyId, book.getId()));
        }

        return copies;
    }

    public Integer getQuantity() { return getCopies().size();}


    private class CopyModel {
        private int copyId;
        private int bookId;

        public CopyModel(int copyId, int bookId) {
            this.copyId = copyId;
            this.bookId = bookId;
        }

        public int getId() {
            return copyId;
        }

        public String getDeleteCopyUrl() {
            return String.format("/library/%d/delete-copy?copyId=%d", bookId, copyId);
        }

    }
    public String getDeleteTitleUrl() {
        return String.format("/library/%d/delete-title",getId());
    }

    public String getEditUrl() {
        return String.format("/library/%d/edit-book",getId());
    }

}

