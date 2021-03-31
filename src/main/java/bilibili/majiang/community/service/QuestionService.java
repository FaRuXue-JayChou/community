package bilibili.majiang.community.service;

import bilibili.majiang.community.dto.QuestionDTO;
import bilibili.majiang.community.mapper.QuestionMapper;
import bilibili.majiang.community.mapper.GithubUserMapper;
import bilibili.majiang.community.model.Question;
import bilibili.majiang.community.model.GithubUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QuestionService
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/23 21:31
 * @Version 1.0
 */
@Service
public class QuestionService {

    @Autowired
    private GithubUserMapper githubUserMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list(Integer pageNum, Integer pageSize){

//        分页查询
        List<Question> questionList = questionMapper.selectPointed((pageNum - 1) * pageSize, pageSize);

//        查询所有
//        List<Question> questionList = questionMapper.selectAll();

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question: questionList){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            GithubUser githubUser = githubUserMapper.findById(question.getCreator());
            questionDTO.setGithubUser(githubUser);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<QuestionDTO> listByUser(GithubUser githubUser, Integer pageNum, Integer pageSize){
        List<Question> questionList = questionMapper.selectPointedByUser(githubUser.getId(), (pageNum - 1) * pageSize, pageSize);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question: questionList){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setGithubUser(githubUser);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public QuestionDTO findById(Integer id) {
        Question question = questionMapper.findById(id);
        GithubUser githubUser = githubUserMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setGithubUser(githubUser);
        return questionDTO;
    }

    public void createOrUpdate(Question question, Integer id){
        if(0 != id){
            question.setGmtModified(System.currentTimeMillis());
            question.setId(id);
            questionMapper.update(question);
        }
        else{
            question.setGmtCreated(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreated());
            questionMapper.create(question);
        }
    }
}