/**  
 * @Project: online
 * @Title: UserRealm.java
 * @Package com.online.owl.shiro
 * @author yuan
 * @date 2016�?�?2�?下午4:53:28
 * @Copyright: 2016
 * @version V1.0  
*/

package com.myssm.yuan.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myssm.yuan.pojo.User;
import com.myssm.yuan.service.IUserService;


/**
 * @ClassName UserRealm
 * @author yuan
 * @version 1.0
 */

public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private IUserService userService;
	
	/** 
	 * 授权
	 * <p>Title: doGetAuthorizationInfo</p> 
	 * <p>Description: </p> 
	 * @param principals
	 * @return 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection) 
	*/
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//未进行授权处理
		return authorizationInfo;
	}

	/** 
	 * 认证
	 * <p>Title: doGetAuthenticationInfo</p> 
	 * <p>Description: </p> 
	 * @param token
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken) 
	*/
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken)token; 
		String account = usernamePasswordToke.getUsername();
		String pwd = String.valueOf(usernamePasswordToke.getPassword());
		User user = this.userService.getUserByAccount(account);
		if( user == null ){
			throw new UnknownAccountException();
		}
		if( !user.getPassword().equals(pwd)){
			throw new IncorrectCredentialsException();
		}
//		if(Boolean.TRUE.equals( user.getLocked())){
//			  throw new LockedAccountException(); //帐号锁定
//		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				account,pwd,this.getName()); //此处未进行密码加密处理
		return authenticationInfo;
	}
	
	 @Override
	    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
	        super.clearCachedAuthorizationInfo(principals);
	    }

	    @Override
	    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
	        super.clearCachedAuthenticationInfo(principals);
	    }

	    @Override
	    public void clearCache(PrincipalCollection principals) {
	        super.clearCache(principals);
	    }

	    public void clearAllCachedAuthorizationInfo() {
	        getAuthorizationCache().clear();
	    }

	    public void clearAllCachedAuthenticationInfo() {
	        getAuthenticationCache().clear();
	    }

	    public void clearAllCache() {
	        clearAllCachedAuthenticationInfo();
	        clearAllCachedAuthorizationInfo();
	    }

}
