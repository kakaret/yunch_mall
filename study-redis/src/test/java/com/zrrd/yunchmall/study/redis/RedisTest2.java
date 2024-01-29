package com.zrrd.yunchmall.study.redis;

import com.zrrd.yunchmall.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
@SuppressWarnings("all")
public class RedisTest2 {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test1() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        ListOperations listOperations = redisTemplate.opsForList();
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        HashOperations hashOperations = redisTemplate.opsForHash();
    }

    //    如果需要操作字符串类型 那么选择 ValueOperations
    @Test
    public void t2() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("author", "LiYe");
        System.out.println("OK");
    }

    //    如何将一个实体类对象信息 保存到redis中存储
    @Test
    public void t3() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Department department = new Department(1, "行政部", "北京");
//        valueOperations.set("dept01", department);
//        这个key将会在40s后过期
        valueOperations.set("dept01", department, 10, TimeUnit.MINUTES);
        System.out.println("OK");
    }

    //    将t3保存的部门信息重新查询到程序中
    @Test
    public void t4() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Department department = (Department) valueOperations.get("dept01");
        System.out.println(department);
        System.out.println("OK");
    }

    //    将多个部门对象同时保存在一个key中
    @Test
    public void t5() {
        ListOperations listOperations = redisTemplate.opsForList();
        List<Department> list = new LinkedList<>();
        list.add(new Department(1, "行政部", "北京"));
        list.add(new Department(2, "财务部", "上海"));
        list.add(new Department(3, "市场部", "广州"));
        list.add(new Department(4, "技术部", "深圳"));
//        一次将整个集合push到redis的list类型的key中
        listOperations.rightPushAll("depts", list);
        System.out.println("OK");
    }

    //    一次将整个list查询到程序中
    @Test
    public void t6() {
        ListOperations listOperations = redisTemplate.opsForList();
        List<Department> list = listOperations.range("depts", 0, -1);
        list.forEach(dept -> System.out.println(dept));
        System.out.println("-----------------------");
//        查找指定下标的对象
        Department department = (Department) listOperations.index("depts", 1);
        System.out.println("第一个位置的部门是：" + department);
        System.out.println("OK");
    }

    //    set类型的使用
    @Test
    public void t7() {
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("fruitSet1", "苹果", "草莓", "香蕉", "梨子");
        setOperations.add("fruitSet2", "苹果", "西瓜", "橙子", "葡萄", "车厘子");
        System.out.println("OK");
    }

    @Test
    public void t8() {
        SetOperations setOperations = redisTemplate.opsForSet();
        long count1 = setOperations.size("fruitSet1");
        System.out.println("水果集1当中有" + count1 + "个水果");
        System.out.println("分别是");
        Set<String> fruitSet1 = setOperations.members("fruitSet1");
        fruitSet1.forEach(fruit -> System.out.println(fruit));


        long count2 = setOperations.size("fruitSet2");
        System.out.println("水果集2当中有" + count2 + "个水果");

        Set<String> intetSet = setOperations.intersect("fruitSet1", "fruitSet2");
        System.out.println("两个水果集的交集有：");
        intetSet.forEach(fruit -> System.out.println(fruit));

        Set<String> unionSet = setOperations.union("fruitSet1", "fruitSet2");
        System.out.println("两个水果集的并集有：");
        unionSet.forEach(fruit -> System.out.println(fruit));

        Set<String> differenceSet = setOperations.difference("fruitSet1", "fruitSet2");
        System.out.println("两个水果集的差集有：");
        differenceSet.forEach(fruit -> System.out.println(fruit));

        System.out.println("OK");
    }

    @Test
    public void t9() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("vegetable", "芥蓝", 7);
        zSetOperations.add("vegetable", "小红萝卜", 2.45);
        zSetOperations.add("vegetable", "青萝卜", 1.6);
        zSetOperations.add("vegetable", "大白菜", 0.98);
        zSetOperations.add("vegetable", "圆葱", 2.97);
        zSetOperations.add("vegetable", "油菜", 5.27);
        zSetOperations.add("vegetable", "平菇", 7.21);
        System.out.println("OK");
    }

    @Test
    public  void t10() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("vegetable", "平菇", 8.21);
//        score的值在原有基础上加上多少
        zSetOperations.incrementScore("vegetable", "大白菜", 0.1);
//        score的值在原有的基础上减去多少
        zSetOperations.incrementScore("vegetable", "小红萝卜", -0.2);
        System.out.println("---------------------");
//        只查value 不看score
//        Set<ZSetOperations.TypedTuple> tupleSet = zSetOperations.range("vegetable", 0, -1);
//        即查询value也查询score
//        Set<ZSetOperations.TypedTuple> tupleSet = zSetOperations.rangeByScoreWithScores("vegetable", 0.0, 200);
//        按照score降序查询
        Set<ZSetOperations.TypedTuple> tupleSet = zSetOperations.reverseRangeByScoreWithScores("vegetable", 0.0, 200);
        tupleSet.forEach(tuple -> System.out.println(tuple.getValue() + "：" + tuple.getScore()));
        System.out.println("OK");
    }

    @Test
    public void t11() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("deptMap", "deptNo", "1");
        hashOperations.put("deptMap", "dName", "技术部");
        hashOperations.put("deptMap", "loc", "哈尔滨");
        System.out.println("OK");
    }

    @Test
    public void t12() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map map = new HashMap();
        map.put("deptno", "2");
        map.put("dname", "市场部");
        map.put("loc", "北京");
        hashOperations.putAll("deptMap2", map);
        System.out.println("OK");
    }

    @Test
    public void t13() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        long size = hashOperations.size("deptMap2");
        if(hashOperations.hasKey("deptMap2", "dname")) {
            System.out.println("有部门名称");
        }
        if(hashOperations.hasKey("deptMap2", "manager")) {
            System.out.println("部门经理" + hashOperations.get("deptMap2", "manager"));
        } else {
            System.out.println("未设置部门经理");
        }
        Map deptInfo = hashOperations.entries("deptMap2");
        System.out.println(deptInfo);
    }

}