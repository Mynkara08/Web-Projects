//package com.zeotap.zeotap_rule_engine.utils;
//
//import com.zeotap.zeotap_rule_engine.models.ASTNode;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ASTUtils {
//    public static ASTNode parseRuleString(String ruleString) {
//        String[] tokens = ruleString.split("(?<=\\()|(?=\\))|\\s+|(?<=[^()\\s])(AND|OR|<=|>=|!=|<|>|=)");
//        List<ASTNode> stack = new ArrayList<>();
//        ArrayList<Object> operators = new ArrayList<>();
//
//        for (String token : tokens) {
//            token = token.trim();
//            if (token.isEmpty()) continue;
//
//            if (token.equals("AND") || token.equals("OR")) {
//                while (!operators.isEmpty() && !operators.getLast().equals("(")) {
//                    popOperator(stack, operators);
//                }
//                operators.add(token);
//            } else if (token.equals("(")) {
//                operators.add(token);
//            } else if (token.equals(")")) {
//                while (!operators.isEmpty() && !operators.getLast().equals("(")) {
//                    popOperator(stack, operators);
//                }
//                operators.removeLast();
//            } else {
//                String operator = null;
//                String value = null;
//
//                if (tokens.length > 1) {
//                    for (int i = 1; true; i++) {
//                        operator = tokens[i];
//                        value = tokens[i + 1];
//                        break;
//                    }
//                }
//
//                stack.add(new ASTNode("operand", token, operator, value));
//            }
//        }
//
//        while (!operators.isEmpty()) {
//            popOperator(stack, operators);
//        }
//
//        return stack.getFirst();
//    }
//
//    private static void popOperator(List<ASTNode> stack, ArrayList<Object> operators) {
//        String operator = (String) operators.removeLast();
//        ASTNode right = stack.removeLast();
//        ASTNode left = stack.removeLast();
////        stack.add(new ASTNode("operator", operator, left, right));
//    }
//
//    public static void printTree(ASTNode node, String prefix, boolean isLeft) {
////        if (node == null) return;
////        System.out.println(prefix + (isLeft ? "├── " : "└── ") + (node.type.equals("operator") ? node.operator : node.key + " " + node.operator + " " + node.value));
////        if (node.left != null) printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
////        if (node.right != null) printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
//    }
//
//    public static ASTNode combineNodes(List<ASTNode> rules, String op) {
//        if (rules.size() == 1) return rules.getFirst();
//
//        ASTNode combined = rules.getFirst();
//        for (int i = 1; i < rules.size(); i++) {
////            combined = new ASTNode("operator", op, combined, rules.get(i));
//        }
//
//        return combined;
//    }
//
//    public static boolean evaluate(ASTNode node, Data data) {
//        if (node.type.equals("operator")) {
//            boolean left = evaluate(node.left, data);
//            boolean right = evaluate(node.right, data);
//            return node.operator.equals("AND") ? left && right : left || right;
//        } else if (node.type.equals("operand")) {
//            String key = node.key;
//            String operator = node.operator;
//            Object valueObj = data.get(key);
//            if (valueObj == null) {
//                return false;
//            }
//
//            String value = node.value;
//            if (value.startsWith("'") && value.endsWith("'")) {
//                value = value.substring(1, value.length() - 1);
//            }
//            double dataValue;
//            if (valueObj instanceof Number) {
//                dataValue = ((Number) valueObj).doubleValue();
//            } else {
//                return false;
//            }
//
//            return switch (operator) {
//                case ">" -> dataValue > Double.parseDouble(value);
//                case "<" -> dataValue < Double.parseDouble(value);
//                case ">=" -> dataValue >= Double.parseDouble(value);
//                case "<=" -> dataValue <= Double.parseDouble(value);
//                case "==", "=" -> String.valueOf(dataValue).equals(value);
//                case "!=" -> !String.valueOf(dataValue).equals(value);
//                default -> false;
//            };
//        }
//        return false;
//    }
//
//    private static class Data {
//        private final Map<String, Object> attributes = new HashMap<>();
//
//        public void set(String key, Object value) {
//            attributes.put(key, value);
//        }
//
//        public Object get(String key) {
//            return attributes.get(key);
//        }
//    }
//
//}
