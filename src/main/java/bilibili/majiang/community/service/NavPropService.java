package bilibili.majiang.community.service;

import bilibili.majiang.community.dto.NavPropDTO;
import bilibili.majiang.community.mapper.QuestionMapper;
import bilibili.majiang.community.model.GithubUser;
import bilibili.majiang.community.model.QuestionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName NavPropService
 * @Description TODO
 * @Author 90855
 * @Date 2021/3/26 15:46
 * @Version 1.0
 */
@Service
public class NavPropService {

    @Autowired
    private QuestionMapper questionMapper;

    public NavPropDTO caculateConfig(Integer pageNum, Integer pageSize){
        Integer dataCount = getCount();
        Integer totalPageNum = getTotalPageNum(dataCount, pageSize);
        if(1 > pageNum)
            pageNum = 1;
        if(pageNum > totalPageNum)
            pageNum = totalPageNum;
        NavPropDTO navPropDTO = new NavPropDTO();
        navPropDTO.setDataCount(dataCount);
        navPropDTO.setPageNum(pageNum);
        navPropDTO.setTotalPageNum(totalPageNum);
        return navPropDTO;
    }

    private Integer getCount(){
        return (int)questionMapper.countByExample(new QuestionExample());
    }

    private Integer getTotalPageNum(Integer dataCount, Integer pageSize){
        if(0 == dataCount % pageSize)
            return dataCount / pageSize;
        else
            return dataCount / pageSize + 1;
    }

    public NavPropDTO caculateConfigByUser(GithubUser githubUser, Integer pageNum, Integer pageSize){
        Integer dataCount = getCountByUser(githubUser);
        Integer totalPageNum = getTotalPageNum(dataCount, pageSize);
        if(1 > pageNum)
            pageNum = 1;
        if(pageNum > totalPageNum)
            pageNum = totalPageNum;
        NavPropDTO navPropDTO = new NavPropDTO();
        navPropDTO.setPageNum(pageNum);
        navPropDTO.setTotalPageNum(totalPageNum);
        navPropDTO.setDataCount(dataCount);
        return navPropDTO;
    }

    private Integer getCountByUser(GithubUser githubUser){
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(githubUser.getId());
        return (int)questionMapper.countByExample(questionExample);
    }

}