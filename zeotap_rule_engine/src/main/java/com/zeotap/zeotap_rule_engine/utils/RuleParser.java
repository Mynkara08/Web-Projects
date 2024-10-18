package com.zeotap.zeotap_rule_engine.utils;

import com.zeotap.zeotap_rule_engine.models.ASTNode;

import java.util.*;

public class RuleParser {

    public ASTNode createAST(String rule) {
        List<String> tokens = tokenizeRule(rule);

        return parseExpression(tokens);
    }
    public ASTNode combineRules(List<String> ruleDefinitions) {
        List<ASTNode> astNodes = new ArrayList<>();
        Map<String, Integer> operatorCount = new HashMap<>();

        for (String rule : ruleDefinitions) {
            ASTNode astNode = createAST(rule);
            astNodes.add(astNode);
            countOperators(astNode, operatorCount);
        }

        String mostFrequentOperator = getMostFrequentOperator(operatorCount);

        return combineASTs(astNodes, mostFrequentOperator);
    }

    private void countOperators(ASTNode node, Map<String, Integer> operatorCount) {
        if (node == null) {
            return;
        }
        if ("operator".equals(node.getType())) {
            operatorCount.put(node.getValue(), operatorCount.getOrDefault(node.getValue(), 0) + 1);
        }
        countOperators(node.getLeft(), operatorCount);
        countOperators(node.getRight(), operatorCount);
    }

    private String getMostFrequentOperator(Map<String, Integer> operatorCount) {
        return operatorCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("AND"); // Default to AND if no operators found
    }

    public ASTNode combineASTs(List<ASTNode> astNodes, String operator) {
        if (astNodes.isEmpty()) {
            return null;
        }

        ASTNode combinedRoot = astNodes.get(0);
        for (int i = 1; i < astNodes.size(); i++) {
            ASTNode operatorNode = new ASTNode();
            operatorNode.setType("operator");
            operatorNode.setValue(operator);
            operatorNode.setLeft(combinedRoot);
            operatorNode.setRight(astNodes.get(i));
            combinedRoot = operatorNode;
        }

        return combinedRoot;
    }

    private List<String> tokenizeRule(String rule) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : rule.toCharArray()) {
            if (c == ' ' || c == '(' || c == ')') {
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                if (c == '(' || c == ')') {
                    tokens.add(String.valueOf(c));
                }
            } else if (c == 'A' || c == 'O') { // For AND, OR
                if (currentToken.length() > 0) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                currentToken.append(c);
            } else {
                currentToken.append(c);
            }
        }
        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private ASTNode parseExpression(List<String> tokens) {
        Stack<ASTNode> nodeStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    ASTNode node = createOperatorNode(operatorStack.pop(), nodeStack);
                    nodeStack.push(node);
                }
                operatorStack.pop(); // Remove the '('
            } else if (token.equals("AND") || token.equals("OR")) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
                    ASTNode node = createOperatorNode(operatorStack.pop(), nodeStack);
                    nodeStack.push(node);
                }
                operatorStack.push(token);
            } else {
                nodeStack.push(createOperandNode(token));
            }
        }

        while (!operatorStack.isEmpty()) {
            ASTNode node = createOperatorNode(operatorStack.pop(), nodeStack);
            nodeStack.push(node);
        }

        return nodeStack.pop();
    }

    private ASTNode createOperandNode(String value) {
        ASTNode node = new ASTNode();
        node.setType("operand");
        node.setValue(value);
        return node;
    }

    private ASTNode createOperatorNode(String operator, Stack<ASTNode> nodeStack) {
        ASTNode node = new ASTNode();
        node.setType("operator");
        node.setValue(operator);

        node.setRight(nodeStack.pop());
        node.setLeft(nodeStack.pop());

        return node;
    }

    private int precedence(String operator) {
        if (operator.equals("AND")) {
            return 2;
        }
        if (operator.equals("OR")) {
            return 1;
        }
        return 0;
    }
}
