package com.example.examprojectspringboot.service.impl;

import com.example.examprojectspringboot.model.Lesson;
import com.example.examprojectspringboot.model.Task;
import com.example.examprojectspringboot.repository.LessonRepository;
import com.example.examprojectspringboot.repository.TaskRepository;
import com.example.examprojectspringboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,LessonRepository lessonRepository) {
        this.taskRepository = taskRepository;
        this.lessonRepository=lessonRepository;
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTask(Long lessonId) {
        return taskRepository.findAllTaskByLessonId(lessonId);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public void saveTask(Long lessonId, Task task) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1 = taskRepository.findById(id).get();
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadLine(task.getDeadLine());
        taskRepository.save(task1);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).get();
        taskRepository.delete(task);
    }
}
