package com.atguigu.springboot;

import com.atguigu.springboot.bean.Article;
/*import io.searchbox.client.JestClient;
import io.searchbox.core.Index;*/
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot03ElasticsearchApplicationTests {

   /* @Autowired
    JestClient jestClient;

    @Test
    void contextLoads() {
        Article article = new Article();
        article.setId(1);
        article.setName("《从零开始》");
        article.setAuthor("雷雨风暴");
        Index index = new Index.Builder(article).index("atguigu").type("news").build();
        try{
            jestClient.execute(index);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

}
