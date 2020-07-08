package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.parser.CSVFileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class
ParseController {
    private static final String PATH = "src/main/java/amazonreviewsapp/springboot/Reviews.csv";

    @Autowired
    private CSVFileParser parser;

    @GetMapping
    @RequestMapping("/parse")
    public String startParsing() {
        parser.parseCsvFile(PATH);
        return "Parsing has been initialized";
    }
}
