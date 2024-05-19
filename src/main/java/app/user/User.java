package app.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    @Column(name = "lastname")
    private String lastName;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_tasks",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks;

    @OneToMany(mappedBy = "issuer")
    private Set<Task> issuedTasks;

//    i have to create one super user for each role. for example, if a banch of workers did one job together
    @OneToMany(mappedBy = "performer")
    private Set<Task> completedTasks;
}
