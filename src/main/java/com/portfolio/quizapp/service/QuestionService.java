package com.portfolio.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.quizapp.dao.QuestionDAO;
import com.portfolio.quizapp.exception_handling.exceptions.ContentNotFoundException;

@Service
public class QuestionService {

  @Autowired
  private QuestionDAO questionDAO;

  public List<Question> getAllQuestions() { 
    return questionDAO.findAll();
  }

  public List<Question> getQuestionsByCategory(String category) {

    List<Question> questions = questionDAO.findByCategory(category);
    if(questions.isEmpty()) {
      throw new ContentNotFoundException();
    }
    return questions;
  }

  public Question getQuestionById(int id) {
    return questionDAO.findById(id).get();
  }


  public Question addQuestion( Question question) {
    if(question == null) {
      throw new IllegalArgumentException();
    }
    return questionDAO.save(question);
  }

  public Question updateQuestion(Question question) {
    if(question == null) {
      throw new IllegalArgumentException();
    }
    if(!questionDAO.existsById((int)question.getId())){
      throw new IllegalArgumentException();
    }
    return questionDAO.save(question);
  }

  public void deleteQuestion(int id) {
    
    if(!questionDAO.existsById(id)){
      throw new IllegalArgumentException();
    }
    questionDAO.deleteById(id);
  }




}
