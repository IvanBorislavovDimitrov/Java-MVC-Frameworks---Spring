package com.ivan.controllers;

import com.ivan.constants.ViewConstants;
import com.ivan.models.binding.DocumentRegisterBindingModel;
import com.ivan.models.service.DocumentRegisterServiceModel;
import com.ivan.models.service.DocumentServiceModel;
import com.ivan.models.view.DocumentViewModel;
import com.ivan.services.api.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ScheduleRegisterController extends BaseController {

    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    public ScheduleRegisterController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/schedule")
    public ModelAndView registerSchedule(Model model) {
        return renderView(ViewConstants.VIEWS_REGISTER_SCHEDULE, model);
    }

    @PostMapping("/schedule")
    public ModelAndView registerScheduleConfirm(@ModelAttribute @Valid DocumentRegisterBindingModel documentRegisterBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return redirect(ViewConstants.REDIRECT_REGISTER_SCHEDULE);
        }

        DocumentViewModel documentViewModel = modelMapper.map(documentService.save(modelMapper.map(documentRegisterBindingModel, DocumentRegisterServiceModel.class)), DocumentViewModel.class);

        return redirect(ViewConstants.REDIRECT_DETAILS + "?id=" + documentViewModel.getId());
    }
}
