package com.example.uks.analytics;

import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LogQueryParser {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("\\(|\\)|\\bAND\\b|\\bOR\\b|\\bNOT\\b|\\w+\\s*:\\s*\"[^\"]+\"|\\w+\\s*:\\s*\\w+");

    public CriteriaQuery parseToCriteriaQuery(String input) {
        List<String> tokens = tokenize(input);
        Deque<Criteria> criteriaStack = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "AND", "OR", "NOT" -> {
                    while (!operatorStack.isEmpty() && precedence(token) <= precedence(operatorStack.peek())) {
                        applyOperator(criteriaStack, operatorStack.pop());
                    }
                    operatorStack.push(token);
                }
                case "(" -> operatorStack.push(token);
                case ")" -> {
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                        applyOperator(criteriaStack, operatorStack.pop());
                    }
                    operatorStack.pop(); // remove '('
                }
                default -> criteriaStack.push(parseSimpleExpression(token));
            }
        }

        while (!operatorStack.isEmpty()) {
            applyOperator(criteriaStack, operatorStack.pop());
        }

        Criteria finalCriteria = criteriaStack.isEmpty() ? new Criteria() : criteriaStack.pop();
        return new CriteriaQuery(finalCriteria);
    }

    private List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(input);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    private int precedence(String op) {
        return switch (op) {
            case "NOT" -> 3;
            case "AND" -> 2;
            case "OR" -> 1;
            default -> 0;
        };
    }

    private void applyOperator(Deque<Criteria> stack, String operator) {
        try {
            if (operator.equals("NOT")) {
                Criteria operand = stack.pop();
                stack.push(operand.not());
            } else {
                Criteria right = stack.pop();
                Criteria left = stack.pop();
                if (operator.equals("AND")) {
                    stack.push(left.and(right));
                } else if (operator.equals("OR")) {
                    stack.push(left.or(right));
                }
            }
        } catch (NoSuchElementException e) {
            // Log error or skip
            System.err.println("Invalid query syntax: " + operator + " without enough operands.");
        }
    }


    private Criteria parseSimpleExpression(String token) {

        if (!token.contains(":")) {
            // fallback kriterijum ako neko unese neispravnu sintaksu
            return new Criteria("message").contains(token);
        }

        String[] parts = token.split(":", 2);
        String field = parts[0];
        String value = parts[1].replaceAll("^\"|\"$", ""); // remove quotes if any

        if (field.equals("message")) {
            return new Criteria(field).contains(value);
        } else {
            return new Criteria(field).is(value);
        }
    }
}
