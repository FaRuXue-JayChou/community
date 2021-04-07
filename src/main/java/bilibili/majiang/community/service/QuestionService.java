package bilibili.majiang.community.service;

import bilibili.majiang.community.dto.QuestionDTO;
import bilibili.majiang.community.mapper.QuestionExtMapper;
import bilibili.majiang.community.mapper.QuestionMapper;
import bilibili.majiang.community.mapper.GithubUserMapper;
import bilibili.majiang.community.model.GithubUserExample;
import bilibili.majiang.community.model.Question;
import bilibili.majiang.community.model.GithubUser;
import bilibili.majiang.community.model.QuestionExample;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public List<QuestionDTO> list(Integer pageNum, Integer pageSize){

//        分页查询
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds((pageNum - 1) * pageSize, pageSize));

//        查询所有
//        List<Question> questionList = questionMapper.selectAll();

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question: questionList){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            GithubUserExample githubUserExample = new GithubUserExample();
            githubUserExample.createCriteria().andIdEqualTo(question.getCreator());
            List<GithubUser> githubUserList = githubUserMapper.selectByExample(githubUserExample);
            questionDTO.setGithubUser(githubUserList.get(0));
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<QuestionDTO> listByUser(GithubUser githubUser, Integer pageNum, Integer pageSize){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(githubUser.getId());
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds((pageNum - 1) * pageSize, pageSize));

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
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        Question question = questionMapper.selectByExampleWithBLOBs(questionExample).get(0);
        GithubUserExample githubUserExample = new GithubUserExample();
        githubUserExample.createCriteria().andIdEqualTo(question.getCreator());
        List<GithubUser> githubUserList = githubUserMapper.selectByExample(githubUserExample);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setGithubUser(githubUserList.get(0));
        return questionDTO;
    }

    public void createOrUpdate(Question question, Integer id){
        if(0 != id){
            question.setGmtModified(System.currentTimeMillis());
            question.setId(id);
            QuestionExample questionExample = new QuestionExample();
            questionMapper.updateByExampleSelective(question, questionExample);
        }
        else{
            question.setGmtCreated(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreated());
            questionMapper.insert(question);
        }
    }

    public int increaseViewCount(Integer id){
        return questionExtMapper.increaseViewCount(id);
    }

}