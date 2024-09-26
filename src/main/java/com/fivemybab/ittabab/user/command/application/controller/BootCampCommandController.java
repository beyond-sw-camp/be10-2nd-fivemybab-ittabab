package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.bootcamp.command.dto.BootCampDTO;
import com.fivemybab.ittabab.user.command.application.service.BootCampService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootCampCommandController {

    private final BootCampService bootcampService;

    @GetMapping("/list")
    public List<BootCampDTO> getAllBootcamps() {
        return bootcampService.findAll();
    }

    @PostMapping("/regist")
    public BootCampDTO createBootcamp(@RequestBody BootCampDTO bootcamp) {
        return bootcampService.save(bootcamp);
    }

    @PutMapping("/modify/{id}")
    public BootCampDTO updateBootcamp(@PathVariable Long id, @RequestBody BootCampDTO bootcamp) {
        return bootcampService.update(id, bootcamp);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBootcamp(@PathVariable Long id) {
        bootcampService.delete(id);
    }
}
