package net.nrasoft.candb.service;

import java.util.Set;

import net.nrasoft.candb.model.Task;

public interface TaskService {

    public Task addTask(Task task);

    public Set<Task> getTasks();

    public Task getTaskByTaskId(Long taskId);

    public Task updateTask(Task task);

    public boolean removeTaskId(Long taskId);

}
