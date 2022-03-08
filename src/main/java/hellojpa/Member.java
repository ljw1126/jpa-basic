package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/*
    요구사항 추가
    1. 회원은 일반 회원과 관리자로 구분해야 함
    2. 회원 가입일과 수정일이 있어야 함
    3. 회원을 설명할 수 있는 필드가 있어야 함. 이 필드는 길이 제한 없음

    @Column
    - unique 속성 : 컬럼에서 잘 안씀 , 사용시 이름이 랜덤으로 생성되서.. @Table 태그에서 사용하고, 이름 설정 가능
    - columnDefinition : 직접 컬럼정의
    - precision, scale : BigDecimal/BigInteger 타입에서 사용 , 숫자/소수점 표시

    @Enumerated
    자바 enum타입 매핑할때 사용, 이때 ORDINAL 사용하지 x
    - EnumType.ORDINAL 속성은 enum 순서(0,1,..)를 DB에 저장 ( default , integer 타입 ) -> 0 번인데, enum에 신규 항목 추가시 이전 0과 새로운 0 구분 불가
    - EnumType.STRING 은 enum 이름(USER,ADMIN)을 DB에 저장 ( varchar 255 타입 )

    @Temporal
    날짜 타입 매핑할 때 사용
    ※ LocalDate, LocalDateTime 사용시 생략가능(최신 hibernate 지원)
    - value
      - TemporalType.DATE : 날짜, db date타입과 매핑 (ex.2022-01-01)
      - TemporalType.TIME : 시간, db time타입과 매핑 (ex.00:00:00)
      - TemporalType.TIMESTAMP : 날짜와 시간, 데이터베이스 timestamp 타입과 매핑 (ex. 2022-01-01 00:00:00)

*/
@Entity
//@Table     // 런타임에 영향 줄 수 있음
public class Member {
    @Id
    private Long id;

    // DDL 생성기능, 런타임/JPA실행로직에 영향 x
    @Column(name="name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY' ")
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)      // varchar(255)로 mapping됨
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob     //String인 경우 CLob, 나머지 BLob 맵핑
    private String description;

    @Transient      // db 쿼리 만들때 제외 , 메모리에서만 사용하는 필드
    private int temp;

    // 최신 hibernateㄴ는 알아서 인식해서 처리함
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    public Member(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
