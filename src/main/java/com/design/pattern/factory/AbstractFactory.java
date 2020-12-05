package com.design.pattern.factory;

public class AbstractFactory {
    public static void main(String[] args) {
        DataSourceHandler dataSourceHandler = new MysqlDataSourceHandler();
        Connection connect = dataSourceHandler.connect();
        connect.connect();
        Command ommand = dataSourceHandler.ommand();
        ommand.command();

    }
}

//工厂方法实现
class MysqlConnection implements Connection {
    @Override
    public void connect() {
        System.out.println("mysql connect");
    }
}
//工厂方法实现
class MysqlCommand implements Command {
    @Override
    public void command() {
        System.out.println("mysql command");
    }
}

class MysqlDataSourceHandler implements DataSourceHandler {
    @Override
    public Connection connect() {
        return new MysqlConnection();
    }

    @Override
    public Command ommand() {
        return new MysqlCommand();
    }
}
//工厂方法
interface Connection {
    void connect();
}
//工厂方法
interface Command {
    void command();
}
//工厂方法的聚合  抽象工厂处理  一组接口的聚合
interface DataSourceHandler {
    Connection connect();
    Command  ommand();
}