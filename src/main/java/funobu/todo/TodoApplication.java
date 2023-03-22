package funobu.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        model.addAttribute("title", "TODO.app");
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

    @PutMapping("/{id}")
    public String updateTodoByID(@PathVariable String id, @RequestParam String content, Model model) {
        todos.forEach(todo -> {
            if (todo.getId().equals(id)) {
                todo.setContent(content);
            }
        });
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTodoByID(@PathVariable String id, Model model) {
        Iterator<Todo> iTodos = todos.iterator();
        while (iTodos.hasNext()) {
            Todo iTodo = iTodos.next();
            if (iTodo.getId().equals(id)) {
                iTodos.remove();
            }
        }
        return "redirect:/";
    }
}
