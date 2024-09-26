package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.query.dto.BootCampDto;
import com.fivemybab.ittabab.user.command.application.service.BootCampCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootCampCommandController {

    private final BootCampCommandService bootcampService;

    @PostMapping("/regist")
    public BootCampDto createBootcamp(@RequestBody BootCampDto bootcamp) {
        return bootcampService.save(bootcamp);
    }

    @PutMapping("/modify/{id}")
    public BootCampDto updateBootcamp(@PathVariable Long id, @RequestBody BootCampDto bootcamp) {
        return bootcampService.update(id, bootcamp);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBootcamp(@PathVariable Long id) {
        bootcampService.delete(id);
    }
}
