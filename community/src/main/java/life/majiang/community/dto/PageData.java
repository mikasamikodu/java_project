package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PageData {

//    所有问题
    private List<QuestionDto> questionDtos;
//    一页的问题数量
    private Integer pageSize;
//    当前页码
    private Integer pageNum;
//    问题总量
    private Integer pageCount;
//    页码数量
    private Integer pagesCount;
//    当前所展示的页码集合
    private List<Integer> pages;

    public List<Integer> setThePages(Integer pageNum,Integer pageCount, Integer pageSize){
        List<Integer> pages = new ArrayList<>();
        if(pageCount%pageSize==0) {
            this.pagesCount = pageCount / pageSize;
        }else{
            this.pagesCount = pageCount / pageSize+1;
        }
        if(pageNum<=0||pageNum>this.pagesCount)
            pageNum = 1;
        this.pageNum = pageNum;
        pages.add(pageNum);
        for(int i=1;i<=3;i++){
            if(pageNum-i>0)
                pages.add(0, pageNum-i);
            if(pageNum+i<=pagesCount)
                pages.add(pageNum+i);
        }
        return pages;
    }
}
