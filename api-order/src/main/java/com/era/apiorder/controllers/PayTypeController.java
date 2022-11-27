package com.era.apiorder.controllers;

import com.era.apiorder.model.services.interfaces.PayTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payTypes")
public class PayTypeController {

    private final PayTypeService payTypeService;

}
