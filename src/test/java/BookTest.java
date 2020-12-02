import entity.Book;
import service.BookCaseService;
import service.BookService;
import service.impl.BookCaseServiceImpl;
import service.impl.BookServiceImpl;

public class BookTest {
    public static void main(String[] args) {
         BookService bookService = new BookServiceImpl();

        Book book =  bookService.findBookById(1);
        BookCaseService bookCaseService = new BookCaseServiceImpl();
        System.out.println(book);
        System.out.println(bookCaseService.findAll());
        String name = "名字";
        String author = "作者";
        String publish = "出版社";
        String pages = "100";
        String price = "100";
        String bookcaseid = "1";
        String abled = "1";
        //插入
        //bookService.insert(name,author,publish,Integer.parseInt(pages),Integer.parseInt(price),Integer.parseInt(bookcaseid), Integer.parseInt(abled));
        //更新
        //bookService.updateById(name,author,publish,Integer.parseInt(pages),Integer.parseInt(price),Integer.parseInt(bookcaseid), Integer.parseInt(abled),108);
    }
}
