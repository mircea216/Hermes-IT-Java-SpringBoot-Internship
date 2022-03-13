package ro.societateahermes.backendservice.controller.controllerImplementation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/__gtg")
@RestController
public class GoodToGoController {

    @GetMapping
    public String goodToGo() {
        System.out.println("OK-MERGE");
        return "OK, merge";
    }
}
