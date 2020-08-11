package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.CSVFileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ParseController {
    @Autowired
    private CSVFileParserService parser;

    @Value("${win.filepath}")
    private String PATH_WIND;

    @Value("${lin.filepath}")
    private String PATH_LIN;

    @GetMapping
    @RequestMapping("/parse")
    public String startParsing() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            parser.parseCsvFile(PATH_WIND);

        } if (System.getProperty("os.name").startsWith("Linux")) {
            parser.parseCsvFile(PATH_LIN);
        }
        return "Parsing has been initialized";
    }
}
