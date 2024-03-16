package net.nrasoft.candb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.nrasoft.candb.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskByTaskId(Long taskId);
}
