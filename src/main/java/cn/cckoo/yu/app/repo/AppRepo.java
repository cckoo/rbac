package cn.cckoo.yu.app.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface AppRepo extends JpaRepository<App, Long> {
}
