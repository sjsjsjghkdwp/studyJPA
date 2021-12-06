package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        System.out.println("왜안돼");
        // 프로젝트 실행시 팩토리 생성, 종료시 close
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 한 유저의 일련의 작업동안 매니저를 생성, 종료시 close
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
    //        member.setId(1L);
    //        member.setName("HelloA");
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
