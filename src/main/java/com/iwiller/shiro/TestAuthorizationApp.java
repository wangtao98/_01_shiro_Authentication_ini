package com.iwiller.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class TestAuthorizationApp {
    private static final transient Logger log = LoggerFactory.getLogger(TestAuthorizationApp.class);


    private static void shiro1(){
        String username = "zhangsan";
        String password = "123456";

        log.info("My First Apache Shiro Application");
        //1.创建安全管理器的工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.使用工厂创建安全管理器
        SecurityManager securityManager = factory.getInstance();
        //3.把当前的安全管理器绑定到当前的线程
        SecurityUtils.setSecurityManager(securityManager);
        //4.使用SecurityUtils.getSubject得到主体对象
        Subject subject = SecurityUtils.getSubject();
        //5.封装用户名和密码
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //6.得到认证
        try {
            subject.login(token);
            System.out.println("认证成功");
        }catch (AuthenticationException e){
            System.out.println("用户名或密码不正确");
        }
    }

    private static void shiro2(){
        String username = "lisi";
        String password = "123456";

        log.info("My First Apache Shiro Application");
        //1.创建安全管理器的工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.使用工厂创建安全管理器
        SecurityManager securityManager = factory.getInstance();
        //3.把当前的安全管理器绑定到当前的线程
        SecurityUtils.setSecurityManager(securityManager);
        //4.使用SecurityUtils.getSubject得到主体对象
        Subject subject = SecurityUtils.getSubject();
        //5.封装用户名和密码
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //6.得到认证
        try {
            subject.login(token);
            System.out.println("认证成功");
        }catch (AuthenticationException e){
            System.out.println("用户名或密码不正确");
        }
//        subject.logout();//退出
        //判断用户是否认证通过
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否通过认证:"+authenticated);

        //角色判断
        boolean hasRole1 = subject.hasRole("role1");
        System.out.println("是否有role1的角色:"+hasRole1);

        List<String> roleIdentifiers = Arrays.asList("role1","role2","role3");
        boolean[] hasRoles = subject.hasRoles(roleIdentifiers);
        for (boolean b :
                hasRoles) {
            System.out.println(b);
        }

        //判断当前用户是否有roleIdentifiers集合里面的所有角色
        boolean hasAllRoles = subject.hasAllRoles(roleIdentifiers);
        System.out.println(hasAllRoles);

        //权限判断
        boolean permitted = subject.isPermitted("user:query");
        System.out.println("判断当前用户是否有user:query:"+permitted);

        boolean[] permitted2 = subject.isPermitted("user:query","user:add","user:export");
        for (boolean b : permitted2) {
            System.out.println(b);
        }

        boolean permittedAll = subject.isPermittedAll("user:query","user:add","user:export");
        System.out.println(permittedAll);
    }


    public static void main(String[] args) {
//        shiro1();
        shiro2();
    }
}
