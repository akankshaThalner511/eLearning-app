package com.expenceTracker.controller;

import com.expenceTracker.DTO.ExpenseDTO;
import com.expenceTracker.DTO.FilterDTO;
import com.expenceTracker.entity.Client;
import com.expenceTracker.entity.Expense;
import com.expenceTracker.service.CategoryService;
import com.expenceTracker.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    ExpenseService expenseService;
    CategoryService categoryService;
  

    @GetMapping("/h")
    public String showHomePage() {
        // Return home page logical view name
        return "landing-page";
    }


//    @Autowired
//    public MainController(ExpenseService expenseService, CategoryService categoryService) {
//        this.expenseService = expenseService;
//        this.categoryService = categoryService;
//    }

    @GetMapping("/")
    public String landingPage(HttpSession session, Model model){
        Client client = (Client) session.getAttribute("client");
        model.addAttribute("sessionClient", client);
        return "landing-page";
    }

    @GetMapping("/showAdd")
    public String addExpense(Model model){
        model.addAttribute("expense", new ExpenseDTO());
        return "add-expense";
    }

    @PostMapping("/submitAdd")
    public String submitAdd(@ModelAttribute("expense") ExpenseDTO expenseDTO, HttpSession session){
        Client client = (Client) session.getAttribute("client");
        expenseDTO.setClientId(client.getId());
        expenseService.save(expenseDTO);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model, HttpSession session){
        Client client = (Client) session.getAttribute("client");
        int clientId = client.getId();
        List<Expense> expenseList = expenseService.findAllExpensesByClientId(clientId);
        for (Expense expense : expenseList){
            expense.setCategoryName(categoryService.findCategoryById(expense.getCategory().getId()).getName());
            expense.setDate(LocalDateTime.parse(expense.getDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalDate().toString());
            expense.setTime(LocalDateTime.parse(expense.getDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalTime().toString());
        }
        model.addAttribute("expenseList", expenseList);
        model.addAttribute("filter", new FilterDTO());
        return "list-page";
    }

    @GetMapping("/showUpdate")
    public String showUpdate(@RequestParam("expId") int id, Model model){
        Expense expense = expenseService.findExpenseById(id);
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setCategory(expense.getCategory().getName());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setDateTime(expense.getDateTime());

        model.addAttribute("expense", expenseDTO);
        model.addAttribute("expenseId", id);
        return "update-page";
    }

    @PostMapping("/submitUpdate")
    public String update(@RequestParam("expId") int id, @ModelAttribute("expense") ExpenseDTO expenseDTO, HttpSession session){
        Client client = (Client) session.getAttribute("client");
        expenseDTO.setExpenseId(id);
        expenseDTO.setClientId(client.getId());
        expenseService.update(expenseDTO);
        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("expId") int id){
        expenseService.deleteExpenseById(id);
        return "redirect:/list";
    }


    @PostMapping("/processFilter")
    public String processFilter(@ModelAttribute("filter") FilterDTO filter, Model model){
        System.out.println("--------------------------------------------------------------");
        System.out.println("filter values : " + filter);
        List<Expense> expenseList = expenseService.findFilterResult(filter);
        System.out.println("size ----> " + expenseList.size());
        System.out.println(expenseList);

        for (Expense expense : expenseList){
            expense.setCategoryName(categoryService.findCategoryById(expense.getCategory().getId()).getName());
            expense.setDate(LocalDateTime.parse(expense.getDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalDate().toString());
            expense.setTime(LocalDateTime.parse(expense.getDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalTime().toString());
        }
        model.addAttribute("expenseList", expenseList);
        return "filter-result";
    }


}