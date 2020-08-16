package amazonreviewsapp.springboot.controller;

import amazonreviewsapp.springboot.service.CSVFileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParseController {
    @Autowired
    private CSVFileParserService parser;

    @Value("${win.filepath}")
    private String pathWin;

    @Value("${lin.filepath}")
    private String pathLin;

    @GetMapping
    @RequestMapping("/parse")
    public String startParsing() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            parser.parseCsvFile(pathWin);

        } if (System.getProperty("os.name").startsWith("Linux")) {
            parser.parseCsvFile(pathLin);
        }
        return "Parsing has been initialized";
    }
}
