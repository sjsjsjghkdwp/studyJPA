package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 프로젝트 실행시 팩토리 생성, 종료시 close
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 한 유저의 일련의 작업동안 매니저를 생성, 종료시 close
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.namer = " + member.getName());
            }
            
            tx.commit();
        } catch (Exception e) {
            System.out.println("몬가 문제가 있음");
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
