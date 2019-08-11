package com.ssm.code.service.Impl;

import com.ssm.code.dao.RegisterMapper;
import com.ssm.code.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * RegisterService class
 *
 * @author Flc
 * @date 2019/5/19
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;


    /**
     * 用于检查学号是否已被注册
     *
     * @param studentId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public int judgeStudentId(String studentId) {
        Integer answer = registerMapper.judgeStudentId(studentId);
        if (answer == 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 注册学号
     *
     * @param stuId
     * @param phone
     * @param password
     * @param timestamp
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public int insertStudent(String stuId, String phone, String password, String timestamp) {
        Integer answer=registerMapper.insertStudent(stuId,phone,password,timestamp);
        if(answer==1){
            return 1;
        }else{
            return -1;
        }
    }
}
