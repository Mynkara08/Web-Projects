package com.zeotap.zeotap_rule_engine.controllers;

import com.zeotap.zeotap_rule_engine.DTO.CombineRuleRequest;
import com.zeotap.zeotap_rule_engine.DTO.CreateRuleRequest;
import com.zeotap.zeotap_rule_engine.DTO.EvaluateRuleRequest;
import com.zeotap.zeotap_rule_engine.models.Rule;
import com.zeotap.zeotap_rule_engine.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rules")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/create_rule")
    public ResponseEntity<?> createRule(@RequestBody CreateRuleRequest req) {
        Rule createdRule = ruleService.createRule(req.getRuleName(), req.getRuleDefinition());
        return new ResponseEntity<>(createdRule, HttpStatus.CREATED);
    }

    @PostMapping("/combine_rules")
    public ResponseEntity<?> combineRules(@RequestBody CombineRuleRequest request) {
        return ResponseEntity.ok(ruleService.combineRules(request.getRules()));
    }


    @PostMapping("/evaluate_rule")
    public ResponseEntity<?> evaluateRule(@RequestBody EvaluateRuleRequest request) {
        return ResponseEntity.ok(ruleService.evaluateRule(request));
    }
}
