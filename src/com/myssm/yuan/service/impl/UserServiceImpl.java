/**  
 * @Project: MySSM
 * @Title: UserServiceImpl.java
 * @Package com.myssm.yuan.service.impl
 * @author yuan
 * @date 2016年6月23日 下午3:46:48
 * @Copyright: 2016
 * @version V1.0  
*/

package com.myssm.yuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myssm.yuan.dao.IUserDao;
import com.myssm.yuan.pojo.User;
import com.myssm.yuan.service.IUserService;

/**
 * @ClassName UserServiceImpl
 * @author yuan
 * @date 2016年6月23日
 * @version 1.0
 */
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	/**
	 * <p>Title: getUserList</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.myssm.yuan.service.IUserService#getUserList() 
	*/
	@Override
	public List<User> getUserList() {
		return this.userDao.getUserList();
	}

}
