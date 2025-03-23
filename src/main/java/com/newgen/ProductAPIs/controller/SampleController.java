package com.newgen.ProductAPIs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgen.ProductAPIs.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SampleController {

    @GetMapping()
    public String sayHello()
    {
        return "Hello developers";
    }

    @GetMapping("/posts")
    public List<Post> getPost()
    {
        RestTemplate restTemplate=new RestTemplate();
       String response= restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",String.class);
        log.info("get post API CALLED :{}" ,response);

        // search the  deleniti word

     //String -> List<Post>
         ObjectMapper objectMapper=new ObjectMapper();

        try
        {
            List<Post> posts= objectMapper.readValue(response,List.class);

           return  posts.stream().filter(post -> post.getTitle().contains("deleniti")).collect(Collectors.toList());

      


        }
        catch (Exception e)
        {
            log.error("Exception occur while parsing the response{}:", e);
        }

        return null;
    }

}
