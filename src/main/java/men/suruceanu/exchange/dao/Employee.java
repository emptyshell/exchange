package men.suruceanu.exchange.dao;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import men.suruceanu.exchange.dao.enums.AccessLevel;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "branch")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long employeeId;

    @OneToMany(mappedBy = "branchId",cascade = CascadeType.ALL)
    private List<Branch> branchId;

    @Column(name = "emp_first_name")
    private String employeeFirstName;

    @Column(name = "emp_last_name")
    private String employeeLastName;

    @Column(name = "emp_birthday")
    private LocalDateTime employeeBirthday;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "emp_access_level")
    private AccessLevel employeeAccessLevel;

    @Column(name = "emp_login")
    private String employeeLogin;

    @Column(name = "emp_password")
    private String employeePassword;
}
