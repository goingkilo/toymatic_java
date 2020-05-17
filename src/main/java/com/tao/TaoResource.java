package com.tao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/tao")
public class TaoResource {

    java.util.Random r = new java.util.Random(System.currentTimeMillis());

    TTC tao;

    @PostConstruct
    public void init() throws IOException {
        tao = getTTCMap();
    }

    @GetMapping
    public String getATaoTeChingSaying() throws IOException {
        int chapter  = r.nextInt(81) + 1;
        String key = (String) tao.keySet().toArray()[chapter];
        String s =  tao.get(key);
        String a =  key + "     -      \n" + s.replaceAll(".", ".<br>");
        return s;

    }

    private TTC getTTCMap() throws IOException {
        String ttc  = getFromClassPath("/ttc.json");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(ttc,TTC.class);
    }

    private String getFromClassPath(String path) throws IOException {
        InputStream inputstream = this.getClass().getResourceAsStream(path);
        String data = readFromInputStream(inputstream);
        return data;
    }

    private String readFromInputStream(InputStream inputStream)
        throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                 = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}
