//package com.example.antiscam;
//
//import com.example.antiscam.dataclass.scamCaseList;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * /表示或
// * &表示与
// * ID scam_id
// *
// * @ user_name
// * post_date
// * # title
// * scam_type
// * description
// * date
// * % amount
// * victim_age
// * victim_city
// */
//public class SearchParser {
//
//    public static List<scamCaseList> search(String searchText, List<scamCaseList> caseList) {
//        searchText = searchText.trim();
//        if (searchText.length() == 0) {
//            return caseList;
//        }
//        String[] searchTerms = searchText.split("&|/");
//
//        List<Character> separators = new ArrayList<>();
//        if (searchTerms.length > 0) {
//            for (int i = 0; i < searchTerms.length; i++) {
//                if (i < searchTerms.length - 1) {
//                    char separator = searchText.charAt(searchTerms[i].length());
//                    separators.add(separator);
//                }
//            }
//            if (separators.size() != searchTerms.length - 1) {
//                return caseList;
//            }
//        }
//
//        List<Set<scamCaseList>> resultByTerms = new ArrayList<>();
//
//
//        for (String term : searchTerms) {
//            Set<scamCaseList> result = new HashSet<>();
//            term = term.trim();
//
//            Pattern pattern = null;
//            if (term.startsWith("ID")) {
//                pattern = Pattern.compile("ID(\\d+)");
//            } else if (term.startsWith("@")) {
//                pattern = Pattern.compile("@(\\w+)");
//            } else if (term.startsWith("#")) {
//                pattern = Pattern.compile("#(\\w+)");
//            } else if (term.startsWith("%")) {
//                pattern = Pattern.compile("%(\\d+(\\.\\d+)?)");
//            }
//
//            if (pattern != null) {
//                Matcher matcher = pattern.matcher(term);
//                if (matcher.find()) {
//                    String value = matcher.group(1);
//                    for (scamCaseList content : caseList) {
//                        if (term.startsWith("ID")) {
//                            if (String.valueOf(content.getScam_id()).equals(value)) {
//                                result.add(content);
//                                break;
//                            }
//                        } else if (term.startsWith("@")) {
//                            if (content.getUser_name().equalsIgnoreCase(value)) {
//                                result.add(content);
//                                break;
//                            }
//                        } else if (term.startsWith("#")) {
//                            if (content.getTitle().toLowerCase().contains(value.toLowerCase())) {
//                                result.add(content);
//                                break;
//                            }
//                        } else if (term.startsWith("%")) {
//                            double amount = Double.parseDouble(value);
//                            if (content.getAmount() == amount) {
//                                result.add(content);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//            resultByTerms.add(result);
//        }
//
//        Set<scamCaseList> result = resultByTerms.get(0);
//        for (int i = 0; i < separators.size(); i++) {
//            char separator = separators.get(i);
//            if (separator == '&') {
//                result.retainAll(resultByTerms.get(i + 1));
//            } else {
//                result.addAll(resultByTerms.get(i + 1));
//            }
//        }
//
//        return new ArrayList<>(result);
//    }
//}
