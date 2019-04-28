package org.sid.dao;

import org.sid.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends JpaRepository<Task, Long>  {

}
