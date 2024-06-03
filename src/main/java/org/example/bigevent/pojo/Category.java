package org.example.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime updateTime;//更新时间

    //如果说某个校验没有指定分组，那么他的分组就是默认分组
    //分组之间可以继承 A extends B 那么B拥有A的所有的校验规则
    public interface Update extends Default {}
    public interface Add extends Default{}
}
