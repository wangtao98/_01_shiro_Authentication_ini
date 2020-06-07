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
        //1.������ȫ�������Ĺ�������
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.ʹ�ù���������ȫ������
        SecurityManager securityManager = factory.getInstance();
        //3.�ѵ�ǰ�İ�ȫ�������󶨵���ǰ���߳�
        SecurityUtils.setSecurityManager(securityManager);
        //4.ʹ��SecurityUtils.getSubject�õ��������
        Subject subject = SecurityUtils.getSubject();
        //5.��װ�û���������
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //6.�õ���֤
        try {
            subject.login(token);
            System.out.println("��֤�ɹ�");
        }catch (AuthenticationException e){
            System.out.println("�û��������벻��ȷ");
        }
    }

    private static void shiro2(){
        String username = "lisi";
        String password = "123456";

        log.info("My First Apache Shiro Application");
        //1.������ȫ�������Ĺ�������
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.ʹ�ù���������ȫ������
        SecurityManager securityManager = factory.getInstance();
        //3.�ѵ�ǰ�İ�ȫ�������󶨵���ǰ���߳�
        SecurityUtils.setSecurityManager(securityManager);
        //4.ʹ��SecurityUtils.getSubject�õ��������
        Subject subject = SecurityUtils.getSubject();
        //5.��װ�û���������
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //6.�õ���֤
        try {
            subject.login(token);
            System.out.println("��֤�ɹ�");
        }catch (AuthenticationException e){
            System.out.println("�û��������벻��ȷ");
        }
//        subject.logout();//�˳�
        //�ж��û��Ƿ���֤ͨ��
        boolean authenticated = subject.isAuthenticated();
        System.out.println("�Ƿ�ͨ����֤:"+authenticated);

        //��ɫ�ж�
        boolean hasRole1 = subject.hasRole("role1");
        System.out.println("�Ƿ���role1�Ľ�ɫ:"+hasRole1);

        List<String> roleIdentifiers = Arrays.asList("role1","role2","role3");
        boolean[] hasRoles = subject.hasRoles(roleIdentifiers);
        for (boolean b :
                hasRoles) {
            System.out.println(b);
        }

        //�жϵ�ǰ�û��Ƿ���roleIdentifiers������������н�ɫ
        boolean hasAllRoles = subject.hasAllRoles(roleIdentifiers);
        System.out.println(hasAllRoles);

        //Ȩ���ж�
        boolean permitted = subject.isPermitted("user:query");
        System.out.println("�жϵ�ǰ�û��Ƿ���user:query:"+permitted);

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
