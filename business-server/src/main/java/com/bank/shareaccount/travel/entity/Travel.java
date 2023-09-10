package com.bank.shareaccount.travel.entity;

import com.bank.shareaccount.group.entity.Group;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Setter
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long travelId;

    @OneToOne(mappedBy = "travel")
    private Group group;

    private String memo;

    public void setMemo(String memo){
        this.memo = memo;
    }
    public void setGroup(Group group){
        this.group=group;
    }
}
