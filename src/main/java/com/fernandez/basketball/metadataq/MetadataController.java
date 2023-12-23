package com.fernandez.basketball.metadataq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/{data}/{atributo}")
    public List<String> getUniqueTeams(
            @PathVariable String data,
            @PathVariable String atributo
    ) {
        List<String> strList = new ArrayList<>();
        if ("results".equals(data) && "homeTeams".equals(atributo)) {
            strList = mongoTemplate.getCollection("RESULTS")
                    .distinct("matchData.home.name", String.class)
                    .into(new ArrayList<>());
        } else if ("results".equals(data) && "awayTeams".equals(atributo)) {
            strList = mongoTemplate.getCollection("RESULTS")
                    .distinct("matchData.away.name", String.class)
                    .into(new ArrayList<>());
        } else if ("fixtures".equals(data) && "homeTeam".equals(atributo)) {
            strList = mongoTemplate.getCollection("FIXTURES")
                    .distinct("homeTeam", String.class)
                    .into(new ArrayList<>());
        } else if ("fixtures".equals(data) && "awayTeam".equals(atributo)) {
            strList = mongoTemplate.getCollection("FIXTURES")
                    .distinct("awayTeam", String.class)
                    .into(new ArrayList<>());
        } else {
            strList = Collections.singletonList("");
        }

        return strList;
    }
}
