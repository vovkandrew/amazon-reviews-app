package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.CSVFileParserService;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParseController {
    @Autowired
    private CSVFileParserService parser;

    @GetMapping
    @RequestMapping("/parse")
    public String startParsing() {
        parser.parseCsvFile("/tmp/Reviews.csv");
        return "Parsing has been initialized";
    }
}
