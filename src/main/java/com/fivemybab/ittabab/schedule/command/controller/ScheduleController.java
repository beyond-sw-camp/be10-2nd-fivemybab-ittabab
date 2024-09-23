package com.fivemybab.ittabab.schedule.command.controller;

import com.fivemybab.ittabab.schedule.command.dto.ScheduleDTO;
import com.fivemybab.ittabab.schedule.command.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /* 전체 일정 조회 */
    @GetMapping("/list")
    public String scheduleList(Model model) {
        List<ScheduleDTO> scheduleList = scheduleService.findScheduleList();
        model.addAttribute("scheduleList", scheduleList);

        return "schedule/list";
    }
    /* 일정 입력 */
    @GetMapping("/regist")
    public void scheduleRegistPage() {}

    @PostMapping("/regist")
    public String registSchedule(@ModelAttribute ScheduleDTO scheduleDTO){

        scheduleService.registSchedule(scheduleDTO);
        return "redirect:/schedule/list";
    }

    /* 일정 수정 */
    @GetMapping("/modify")
    public void scheduleModifyPage() {}

    @PostMapping("/modify")
    public String modifySchedule(@ModelAttribute ScheduleDTO scheduleDTO){
        scheduleService.modifySchedule(scheduleDTO);
        return "redirect:/schedule/list";
    }

    /* 일정 삭제 */
    @GetMapping("/delete")
    public void scheduleDeletePage() {}

    @PostMapping("/delete")
    public String deleteSchedule(@RequestParam Long scheduleId){
        scheduleService.deleteSchedule(scheduleId);
        return "redirect:/schedule/list";
    }
}
