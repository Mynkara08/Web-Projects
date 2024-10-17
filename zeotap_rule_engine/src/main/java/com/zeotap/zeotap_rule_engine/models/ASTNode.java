package com.zeotap.zeotap_rule_engine.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class ASTNode {
    public String type;
    public String operator;
    public ASTNode left;
    public ASTNode right;
    public String key;
    public String value;
    public ASTNode(String type, String operator, ASTNode left, ASTNode right) {
        this.type = type;
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
    public ASTNode(String type, String key, String operator, String value) {
        this.type = type;
        this.key = key;
        this.operator = operator;
        this.value = value;
    }
}

