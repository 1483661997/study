package edu.bit.study.DynamicProxy;

public class DoCglib {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        UserServiceImpl proxyImp = (UserServiceImpl) proxy.getProxy(UserServiceImpl.class);
        proxyImp.add();
    }
}
