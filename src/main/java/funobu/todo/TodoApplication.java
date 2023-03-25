package funobu.todo;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Todo> todos = new ArrayList<Todo>();
        try {
            Iterator<Todo> result = this.todoRepository.findAll().iterator();
            while (result.hasNext()) {
                todos.add(result.next());
            }
        } catch (Exception e) {

        }
        model.addAttribute("title", "TODO.app");
        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new Todo());
        return "/index";
    }

    @PostMapping("/")
    public String postIndex(@ModelAttribute Todo newTodo, Model model) {
        try {
            newTodo.setId(UUID.randomUUID().toString());
            this.todoRepository.save(newTodo);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String updateTodoByID(@PathVariable String id, @RequestParam String content, Model model) {
        try {
            Todo todo = this.todoRepository.findById(id).get();
            todo.setContent(content);
            this.todoRepository.save(todo);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTodoByID(@PathVariable String id, Model model) {
        try {
            Todo todo = this.todoRepository.findById(id).get();
            this.todoRepository.delete(todo);
        } catch (Exception e) {
            System.out.println(e);
        }

        return "redirect:/";
    }
}
