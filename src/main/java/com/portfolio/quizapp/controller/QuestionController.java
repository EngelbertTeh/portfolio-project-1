package com.portfolio.quizapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.portfolio.quizapp.Constants;
import com.portfolio.quizapp.service.Question;
import com.portfolio.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController<T> {

  @Autowired
  private QuestionService questionService;

  @GetMapping("allQuestions") 
  public ResponseEntity<List<Question>> getAllQuestions(){
    return  ResponseEntity.ok(questionService.getAllQuestions());
  }

  @GetMapping("category/{category}")
  public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
    return ResponseEntity.ok(questionService.getQuestionsByCategory(category));
  }


  @GetMapping("{id}")
  public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
    return ResponseEntity.ok(questionService.getQuestionById(id));
  }


  @PostMapping("add")
  public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
    Question savedQuestion = questionService.addQuestion(question);
    URI location = UriComponentsBuilder.fromPath(Constants.Uri + "question/" + savedQuestion.getId()).buildAndExpand().toUri();
    return ResponseEntity.created(location).body(savedQuestion);
  }

  @PutMapping("update")
  public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
    return ResponseEntity.ok(questionService.updateQuestion(question));
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable int id) {
     questionService.deleteQuestion(id);
     return ResponseEntity.noContent().build();
  }
}
