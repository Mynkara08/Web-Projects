package com.zeotap.zeotap_rule_engine.controllers;

import com.zeotap.zeotap_rule_engine.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> createRule(@RequestBody Rule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @PostMapping("/combine_rules")
    public ResponseEntity<?> combineRules(@RequestBody CombineRulesRequest request) {
        return ResponseEntity.ok(ruleService.combineRules(request));
    }

    @PostMapping("/evaluate_rule")
    public ResponseEntity<?> evaluateRule(@RequestBody EvaluateRuleRequest request) {
        return ResponseEntity.ok(ruleService.evaluateRule(request));
    }
}
