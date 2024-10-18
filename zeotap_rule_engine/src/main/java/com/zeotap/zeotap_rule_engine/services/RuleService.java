package com.zeotap.zeotap_rule_engine.services;

import com.zeotap.zeotap_rule_engine.DTO.EvaluateRuleRequest;
import com.zeotap.zeotap_rule_engine.DTO.EvaluatedRulesResponse;
import com.zeotap.zeotap_rule_engine.models.*;
import com.zeotap.zeotap_rule_engine.repositories.RuleRepository;
import com.zeotap.zeotap_rule_engine.utils.RuleParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    private final RuleRepository ruleRepository;
    RuleParser ruleParser;
    @Autowired
    public RuleService(RuleRepository ruleRepository,RuleParser ruleParser){
        this.ruleRepository=ruleRepository;
        this.ruleParser=ruleParser;
    }
    public Rule createRule(String ruleName, String ruleDefination) {
        Rule rule = new Rule();
        rule.setRuleName(ruleName);
        rule.setRuleDefinition(ruleDefination);
        return ruleRepository.save(rule);
    }

    public ASTNode combineRules(List<String> rules) {
        return ruleParser.combineRules(rules);
    }

    public EvaluatedRulesResponse evaluateRule(EvaluateRuleRequest request) {
        EvaluatedRulesResponse res = null;
        res.setResult(true);
        return res;
    }
}