package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.CSVFileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class ParseController {
    @Autowired
    private CSVFileParserService parser;

    @GetMapping
    @RequestMapping("/parse")
    public String startParsing() {
        File file = new File(getClass().getClassLoader().getResource("Reviews.csv").getFile());
        parser.parseCsvFile(file.getPath());
        return "Parsing has been initialized";
    }
}
