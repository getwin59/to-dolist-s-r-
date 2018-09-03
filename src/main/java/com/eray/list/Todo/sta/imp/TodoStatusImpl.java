package com.eray.list.Todo.sta.imp;

import java.lang.reflect.Type;
import java.util.List;

import com.eray.list.Todo.rep.TodoRepository;
import com.eray.list.Todo.sta.TodoStatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eray.list.Todo.controller.item.TodoItem;
import com.eray.list.Todo.entity.TodoItemEntity;

@Component
public class TodoStatusImpl implements TodoStatus {
	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<TodoItem> getTodoItems() {
		List<TodoItemEntity> list = todoRepository.findAll();
		Type type = new TypeToken<List<TodoItem>>() {}.getType();
		return modelMapper.map(list, type);
	}

	@Override
	public TodoItem addTodoItem(TodoItem todoItem) {
		return modelMapper.map(todoRepository.save(modelMapper.map(todoItem, TodoItemEntity.class)), TodoItem.class);
	}

	@Override
	public void deleteTodoItem(Integer id) {
		todoRepository.delete(id);
	}

	@Override
	public List<TodoItem> getTodoItems(boolean complete) {
		
		List<TodoItemEntity> list = todoRepository.findByComplete(complete);
		Type type = new TypeToken<List<TodoItem>>() {}.getType();
		return modelMapper.map(list, type);
	}

	@Override
	public void changeTodoItemStatus(Integer id, String status) {
		TodoItemEntity todoItemEntity = todoRepository.findById(id);
		if(null != todoItemEntity) {
			if(status != null && status.equals("pending")) {
				todoItemEntity.setComplete(false);
			}else {
				todoItemEntity.setComplete(true);
			}
			
			todoRepository.save(todoItemEntity);
			
		}
	}

}
