package com.eray.list.Todo.controller;

import java.util.List;

import com.eray.list.Todo.sta.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eray.list.Todo.controller.item.TodoItem;

@RestController
@RequestMapping("/todolist")
public class TodoController {
	
	@Autowired
	private TodoStatus todoStatus;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/lists")
	public List<TodoItem> getTodoItems(@RequestParam(required=false) Boolean complete){
		if(null == complete) {
			return todoStatus.getTodoItems();
		}else {
			return todoStatus.getTodoItems(complete);
		}
	}
	
	@PostMapping("/lists/{add}")
	public TodoItem addTodoItem(@RequestBody TodoItem todoItem ){
		return todoStatus.addTodoItem(todoItem);
		
	}
	@DeleteMapping("lists/{id}")
	public void deleteTodoItem(@PathVariable Integer id) {
		todoStatus.deleteTodoItem(id);
	}
	@PostMapping("/todo/id/{id}/status/{status}")
	public void changeTodoItemStatus(@PathVariable Integer id, @PathVariable String status){
		
		  todoStatus.changeTodoItemStatus(id,status);;
		
	}


}
