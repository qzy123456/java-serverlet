package controller;

import entity.Admin;
import entity.Book;
import entity.BookCase;
import entity.Borrow;
import service.BookCaseService;
import service.BookService;
import service.impl.BookCaseServiceImpl;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    private BookCaseService bookCaseService = new BookCaseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bashPath = req.getContextPath();
        String method = req.getParameter("method");
        if(method == null){
            method = "findAllBorrow";
        }
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        switch (method){
            case "findAllBorrow":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Borrow> borrowList = bookService.findAllBorrowByState(0,page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(0));
                req.getRequestDispatcher("admin.jsp").forward(req,resp);
                break;
            case "handle":
                String idStr = req.getParameter("id");
                String stateStr = req.getParameter("state");
                Integer id = Integer.parseInt(idStr);
                Integer state = Integer.parseInt(stateStr);
                bookService.handleBorrow(id,state,admin.getId());
                if(state == 1 || state == 2){
                    resp.sendRedirect(bashPath+"/admin?page=1");
                }
                if(state == 3){
                    resp.sendRedirect(bashPath+"/admin?method=getBorrowed&page=1");
                }
                break;
            case "getBorrowed":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                borrowList = bookService.findAllBorrowByState(1,page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(1));
                req.getRequestDispatcher("return.jsp").forward(req,resp);
                break;
            case "findAll":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                List<Book> list = bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("adminIndex.jsp").forward(req,resp);
                break;
            case "del":
                idStr = req.getParameter("bookid");
                id = Integer.parseInt(idStr);
                bookService.del(id);
                page = 1;
                list = bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("adminIndex.jsp").forward(req,resp);
                break;
            case "add":
                req.setAttribute("bookcases",bookCaseService.findAll());
                req.getRequestDispatcher("add.jsp").forward(req,resp);
                break;
            case "update":
                idStr = req.getParameter("bookid");
                id = Integer.parseInt(idStr);
                Book book =  bookService.findBookById(id);
                req.setAttribute("book",book);
                req.setAttribute("bookcases",bookCaseService.findAll());
                req.getRequestDispatcher("add.jsp").forward(req,resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bashPath = req.getContextPath();
       req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        if(method == null){
            method = "add";
        }

        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        switch (method){
            case "add":
                String idStr = req.getParameter("id");
                System.out.println(idStr);
                String name = req.getParameter("name");
                String author = req.getParameter("author");
                String publish = req.getParameter("publish");
                String pages = req.getParameter("pages");
                String price = req.getParameter("price");
                String bookcaseid = req.getParameter("bookcaseid");
                String abled = req.getParameter("abled");
                if(idStr.equals("")){
                    bookService.insert(name,author,publish,Integer.parseInt(pages),Integer.parseInt(price),Integer.parseInt(bookcaseid),
                            Integer.parseInt(abled));
                }else{
                    int prices = new Double(price).intValue();
                    bookService.updateById(name,author,publish,Integer.parseInt(pages),prices,Integer.parseInt(bookcaseid),
                            Integer.parseInt(abled),Integer.parseInt(idStr));
                }
                //跳转图书列表
                int  page = 1;
                List<Book> list = bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("adminIndex.jsp").forward(req,resp);
                break;
        }
    }
}
