package funobu.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

@Controller
@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    public TodoApplication() {
        todos = new ArrayList<Todo>();
    }

    private List<Todo> todos;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "みこち");
        model.addAttribute("todos", this.todos);
        model.addAttribute("newTodo", new Todo());
        return "/index";
    }

    @PostMapping("/")
    public String postIndex(@ModelAttribute Todo newTodo, Model model) {
        newTodo.setId(UUID.randomUUID().toString());
        this.todos.add(newTodo);
        System.out.println(newTodo.getContent());
        return "redirect:/";
    }
}
