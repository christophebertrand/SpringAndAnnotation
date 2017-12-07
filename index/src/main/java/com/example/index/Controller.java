package com.example.index;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
public class Controller {

    private Multimap<String, Long> map = ArrayListMultimap.create();


    @RequestMapping("user/like")
    public Iterable<Long> like(@RequestParam("name") String pattern) {
        System.out.println("called user/like " +  pattern);
       return map.entries().stream().filter(e -> e.getKey().startsWith(pattern))
               .map(Map.Entry::getValue).collect(Collectors.toList());
//               .map(l -> new Body(l.getKey(), l.getValue())).collect(Collectors.toList());
    }

    @RequestMapping(value="delete", method = DELETE)
    public ResponseEntity<?> delete() {
        map = ArrayListMultimap.create();
        return ResponseEntity.status(200).build();
    }

    @RequestMapping("user")
    public Collection<Body> find(@RequestParam(value = "name") String name) {
      return map.get(name).stream().map(l -> new Body(name, l)).collect(Collectors.toList());
    }

    @RequestMapping(value = "add", method = POST)
    public ResponseEntity<?> post(@RequestBody Body body) {
       map.put(body.getFirstName(), body.getId());
       return ResponseEntity.status(201).build();
    }

}
