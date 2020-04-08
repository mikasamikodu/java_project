package life.majiang.community.dto;

import lombok.Data;

@Data
public class QuestionDto {

    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Integer creatorId;
    private String avatarUrl;
}
