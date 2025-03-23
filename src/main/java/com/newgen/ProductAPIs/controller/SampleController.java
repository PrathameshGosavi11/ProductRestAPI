package com.newgen.ProductAPIs.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgen.ProductAPIs.config.RestTemplateProvider;
import com.newgen.ProductAPIs.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SampleController {

    //   private RestTemplate  restTemplate=new RestTemplate();
    
    @Autowired
//    private RestTemplateProvider restTemplateProvider;
    private RestTemplate restTemplate; //singleton bean

    @GetMapping()
    public String sayHello()
    {
        return "Hello developers";
    }

    @GetMapping("/posts")
    public List<Post> getPost()
    {
        //RestTemplate restTemplate=new RestTemplate();


            //RestTemplate restTemplate=restTemplateProvider.getRestTemplate();
            String response = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
            log.info("get post API CALLED :{}", response);


        // search the  deleniti word

     //String -> List<Post>
         ObjectMapper objectMapper=new ObjectMapper();

        try
        {
            List<Post> posts = objectMapper.readValue(response, new TypeReference<List<Post>>() {
            });



           return  posts.stream().filter(post -> post.getTitle().contains("deleniti")).collect(Collectors.toList());

        //    return posts;


        }
        catch (Exception e)
        {
            log.error("Exception occur while parsing the response{}:", e);
        }

        return null;
    }

    @PostMapping()
    public ResponseEntity<?> addPost(@RequestBody Post post)
    {
       // RestTemplate restTemplate=new RestTemplate();
   //     RestTemplate restTemplate=restTemplateProvider.getRestTemplate();
        String url="https://jsonplaceholder.typicode.com/posts";
        return  restTemplate.postForEntity(url,new Post(),Post.class);

    }

}
