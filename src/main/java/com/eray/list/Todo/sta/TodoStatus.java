package com.eray.list.Todo.sta;

import java.util.List;

import com.eray.list.Todo.controller.item.TodoItem;

public interface TodoStatus {

	List<TodoItem> getTodoItems();

	TodoItem addTodoItem(TodoItem todoItem);

	void deleteTodoItem(Integer id);

	List<TodoItem> getTodoItems(boolean complete);

	void changeTodoItemStatus(Integer id, String status);

}
