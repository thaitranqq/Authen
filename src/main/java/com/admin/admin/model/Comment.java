package com.admin.admin.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private Long productitemid;
    private LocalDateTime commentdate;
    private String content;

    public Comment(String userid, Long productItemId, LocalDateTime commentDate, String content) {
        this.userid = userid;
        this.productitemid = productItemId;
        this.commentdate = commentDate;
        this.content = content;
    }
}
