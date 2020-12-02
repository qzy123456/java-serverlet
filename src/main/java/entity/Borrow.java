package entity;

public class Borrow {
    private Integer id;
    private entity.Book book;
    private entity.Reader reader;
    private String borrowTime;
    private String returnTime;
    private Integer state;

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public entity.Book getBook() {
        return book;
    }

    public void setBook(entity.Book book) {
        this.book = book;
    }

    public entity.Reader getReader() {
        return reader;
    }

    public void setReader(entity.Reader reader) {
        this.reader = reader;
    }

    public Borrow(Integer id, entity.Book book, entity.Reader reader, String borrowTime, String returnTime, Integer state) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.state = state;
    }
    public Borrow(Integer id) {
        this.id = id;
    }
}
