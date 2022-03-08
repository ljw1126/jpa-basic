package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                        Persistence.createEntityManagerFactory("hello");//지정된 경로에 있는 persisten.xml의 name 속성
        // JPA에서는 모든 작업을 트랜잭션 안에서 작업해야 함
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //맴버 저장

            Member member = new Member();
            member.setId(1L);
            member.setName("charlie");
            member.setRoleType(RoleType.USER);

            em.persist(member);

            member = new Member();
            member.setId(2L);
            member.setName("may");
            member.setRoleType(RoleType.ADMIN);

            em.persist(member);

            // 조회
            /*Member findMember = em.find(Member.class,1L);
            System.out.println(findMember.toString());*/

            // 삭제 em.remove(찾은객체)

            // 수정
            Member findMember = em.find(Member.class,1L);
            findMember.setName("May");// 자바 객체 값을 바꿨는데 DB데이터가 변경
            // JPA 로 불러오면 트랜잭션 종료 전가지 JPA에서 관리하고 변경 발생하면 update 쿼리 날림

            //jpql
            List<Member> members = em.createQuery("select m from Member as m", Member.class)
                                     .getResultList();
            members.forEach(System.out::println);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
